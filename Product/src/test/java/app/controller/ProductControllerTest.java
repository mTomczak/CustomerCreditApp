package app.controller;

import app.model.Product;
import app.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ProductControllerTest {

    @Autowired
    ProductRepository productRepository;
    @Autowired
    ProductController productController;
    @Autowired
    Product product;
    @Autowired
    Product testProduct;

    @Test
    public void createProductAndGetProductTest(){

        product.setProductName("Product");
        product.setValue(15);

        productController.createProduct(product);

        testProduct = productController.getProduct("Product");

        assertEquals(product.getProductName(), testProduct.getProductName());
        assertEquals(product.getValue(), testProduct.getValue());




    }

}