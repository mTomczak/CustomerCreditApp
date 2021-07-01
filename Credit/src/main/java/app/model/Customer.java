package app.model;

import org.springframework.stereotype.Component;

import java.io.Serializable;

/**
 * The class that describes the customer object
 */
@Component
public class Customer implements Serializable {

    private int ID;
    private String firstName;
    private String surname;
    private String pesel;

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPesel() {
        return pesel;
    }

    public void setPesel(String pesel) {
        this.pesel = pesel;
    }
}
