package app.Controller;

import app.model.Customer;

import app.repository.CustomerRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CustomerControllerTest {



    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    Customer customer;

    @Autowired
    Customer testCustomer;

    @Autowired
    CustomerController customerController;

    @Test
    public void createCustomerAndGetCustomerTest(){

        customer.setFirstName("firstName");
        customer.setSurname("surname");
        customer.setPesel("123");

        customerController.createCustomer(customer);

        testCustomer = customerController.getCustomer("123");

        assertEquals(customer.getFirstName(), testCustomer.getFirstName());
        assertEquals(customer.getSurname(), testCustomer.getSurname());
        assertEquals(customer.getPesel(), testCustomer.getPesel());

    }

}