package app.Wraper;


import app.model.Customer;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * The class that wraps the Customer List.
 */

@Component
public class CustomerWraper {
    private List<Customer> customerList;

    public List<Customer> getCustomerList() {
        return customerList;
    }

    public void setCustomerList(List<Customer> customerList) {
        this.customerList = customerList;
    }

    public CustomerWraper (){
        this.customerList = new ArrayList<>();
    }
}
