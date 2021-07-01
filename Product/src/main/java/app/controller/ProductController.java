package app.controller;


import app.SpringRestBootApplicationProduct;
import app.model.Product;
import app.repository.ProductRepository;
import app.wraper.ProductWraper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * The class that controls the handling of the product. Writing and reading from the database.
 */

@Controller
@RestController
public class ProductController {


    private ProductRepository productRepository;
    private Logger logger = LoggerFactory.getLogger(SpringRestBootApplicationProduct.class);

    @Autowired
    Product product;

    @Autowired
    ProductWraper productWraper;


    @Autowired
    public ProductController(ProductRepository productRepository){
        this.productRepository = productRepository;
    }

    /**
     * A method to check if the website is working.
     * @return endpoint returning the string "Customer Module - working"
     */
    @RequestMapping("/isitworking")
    public String test(){
        return "Product Module - working";
    }

    /**
     * The method checks if the product is in the database, if not, it saves it to the database
     * @param product product
     */

    @RequestMapping(value = "/createProduct", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    public void createProduct(
            @RequestBody() Product product){
        logger.info("Product -  received product: " + product.getProductName());
        this.product = product;

        if(productRepository.findByProductName(product.getProductName()) == null) {
            productRepository.save(product);
            logger.info("Product - saved to DB");

        }
    }

    /**
     * The method of retrieving the product from the database based on the name of the product.
     * @param productName productName
     * @return Returns the product object
     */

    @RequestMapping("/getProduct/{productName}")
    @ResponseStatus(HttpStatus.OK)
    public Product getProduct(@PathVariable(value = "productName") String productName){
        return  productRepository.findByProductName(productName);

    }

    /**
     * A method that downloads a list of all products from the database. It returns it wrapped in a ProductWrapel
     * @return ProductWraper
     */

    @RequestMapping("/getallproduct")
    @ResponseStatus(HttpStatus.OK)
    public ProductWraper getAllProducts(){

        for (Product productRepoList:
                productRepository.findAll()) {
            productWraper.getProductList().add(productRepoList);
        }

        return  productWraper;
    }

}
