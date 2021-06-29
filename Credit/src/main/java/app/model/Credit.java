package app.model;

import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

@Entity
@Table
@Component
public class Credit implements Serializable {


    @Id
    @GeneratedValue
    @NonNull
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
