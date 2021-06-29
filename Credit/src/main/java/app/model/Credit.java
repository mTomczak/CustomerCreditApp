package app.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table
public class Credit implements Serializable {


    @Id
    @GeneratedValue
    private int ID;
    private String creditName;
    private int CustomerID;
    private int ProudctID;


    public String getCreditName() {
        return creditName;
    }

    public void setCreditName(String creditName) {
        this.creditName = creditName;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getCustomerID() {
        return CustomerID;
    }

    public void setCustomerID(int customerID) {
        CustomerID = customerID;
    }

    public int getProudctID() {
        return ProudctID;
    }

    public void setProudctID(int proudctID) {
        ProudctID = proudctID;
    }

    public Credit(){};

    public Credit(String creditName) {
        this.creditName = creditName;
    }
}
