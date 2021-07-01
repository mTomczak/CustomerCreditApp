package app.Controller;

import app.repository.CustomerRepository;
import app.model.Customer;
import app.wraper.CustomerWraper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * The class that controls customer events. Creating a client, downloading information about the client .
 */

@Controller
@RestController
public class CustomerController {



    @Autowired
    Customer customer;

    @Autowired
    CustomerWraper customerWraper;

    private CustomerRepository customerRepository;

    @Autowired
    public CustomerController(CustomerRepository customerRepository){
        this.customerRepository = customerRepository;
    }

    /**
     * A method to check if the website is working.
     * @return endpoint returning the string "Customer Module - working"
     */
    @RequestMapping("/isitworking")
    public String test(){
        return "Customer Module - working";
    }


    /**
     *The method checks if the client with the given pesel number is in the database, if not, it creates one
     * @param customer customer
     */
    @RequestMapping(value = "/createCustomer", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    public void createCustomer(
            @RequestBody() Customer customer){
        this.customer = customer;

           if(customerRepository.findByPesel(customer.getPesel()) == null) {
               customerRepository.save(customer);


           }
    }


    /**
     * A method that retrieves data about the client based on the pelsel number it takes as a parameter. Returns a client object
     * @param pesel pesel
     * @return Customer
     */

    @RequestMapping("/getcustomer/{pesel}")
    @ResponseStatus(HttpStatus.OK)
    public Customer getCustomer(@PathVariable(value = "pesel") String pesel){
        return  customerRepository.findByPesel(pesel);

    }


    /**
     * A method that gets a list of all clients. Returns a list wrapped in a CustomerWraper object
     * @return CustomerWraper
     */

    @RequestMapping("/getallcustomer")
    @ResponseStatus(HttpStatus.OK)
    public CustomerWraper getAllCustomer(){

        for (Customer customerRepoList:
             customerRepository.findAll()) {
            customerWraper.getCustomerList().add(customerRepoList);
        }

        return  customerWraper;
    }

}
