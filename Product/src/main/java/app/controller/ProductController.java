package app.controller;


import app.SpringRestBootApplicationProduct;
import app.model.Product;
import app.repository.ProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RestController
public class ProductController {


    private ProductRepository productRepository;
    private Logger logger = LoggerFactory.getLogger(SpringRestBootApplicationProduct.class);

    @Autowired
    Product product;


    @Autowired
    public ProductController(ProductRepository productRepository){
        this.productRepository = productRepository;
    }

    @RequestMapping("/isitworking")
    public String test(){
        return "isitworking";
    }


    @RequestMapping(value = "/createProduct", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    public void createCustomer(
            @RequestBody() Product product){
        logger.info("Product -  podany product: " + product.getProductName());
        this.product = product;

        try {
            logger.info("Product - znaleziony w bazie : " + productRepository.findByProductName(product.getProductName()).getProductName());
        }catch (NullPointerException e){

        }

        if(productRepository.findByProductName(product.getProductName()) == null) {
            productRepository.save(product);
            logger.info("Product - zapisany do bazy");

        }
    }

    @RequestMapping("/getProduct/{productName}")
    @ResponseStatus(HttpStatus.OK)
    public Product getProduct(@PathVariable(value = "productName") String productName){
        return  productRepository.findByProductName(productName);

    }


    @RequestMapping("/getAllProduct")
    @ResponseStatus(HttpStatus.GONE)
    public List<Product> getCredits(){

        return  productRepository.findAll();
    }

}
