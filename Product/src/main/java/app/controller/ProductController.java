package app.controller;


import app.model.Product;
import app.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Controller
@RestController
public class ProductController {


    private ProductRepository productRepository;

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


    @RequestMapping("/putProduct")
    @ResponseStatus(HttpStatus.CREATED)
    public Product createcredit(
            @RequestParam() Product product){

        this.product = product;

           if(productRepository.findByProductName(product.getProductName()) != null) {
               productRepository.save(product);
           }

        return productRepository.findByProductName(product.getProductName());

    }



    @RequestMapping("/getAllProduct")
    @ResponseStatus(HttpStatus.GONE)
    public List<Product> getCredits(){

        return  productRepository.findAll();
    }

}
