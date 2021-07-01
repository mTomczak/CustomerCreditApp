package app.model;

import org.springframework.stereotype.Component;

import java.io.Serializable;



/**
 * The class that describes the product object
 */
@Component
public class Product implements Serializable {

    private int ID;
    private String productName;
    private float value;



    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public float getValue() {
        return value;
    }

    public void setValue(float value) {
        this.value = value;
    }

}
