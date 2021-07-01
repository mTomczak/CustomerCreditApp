package app.repository;


import app.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * A class that handles database queries for the Product.class object
 */

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    /**
     * Customer return method
     * @param productName = productName
     * @return Credit
     */


    Product findByProductName(String productName);



}
