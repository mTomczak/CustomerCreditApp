package app.Wraper;


import app.model.Product;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * The class that wraps the Product List.
 */
@Component
public class ProductWraper {
    private List<Product> productList;

    public List<Product> getProductList() {
        return productList;
    }

    public void setProductList(List<Product> productList) {
        this.productList = productList;
    }

    public ProductWraper(){
        this.productList = new ArrayList<>();
    }
}
