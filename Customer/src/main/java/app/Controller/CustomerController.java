package app.Controller;

import app.repository.CustomerRepository;
import app.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Controller
@RestController
public class CustomerController {


    private CustomerRepository customerRepository;

    @Autowired
    Customer customer;


    @Autowired
    public CustomerController(CustomerRepository customerRepository){
        this.customerRepository = customerRepository;
    }

    @RequestMapping("/isitworking")
    public String test(){
        return "isitworking";
    }


    @RequestMapping("/putCustomer")
    @ResponseStatus(HttpStatus.CREATED)
    public Customer createcredit(
            @RequestParam() Customer customer){

        this.customer = customer;

           if(customerRepository.findByPesel(customer.getPesel()) != null) {
               customerRepository.save(customer);
           }

        return customerRepository.findByPesel(customer.getPesel());

    }



    @RequestMapping("/getAllCustomer")
    @ResponseStatus(HttpStatus.GONE)
    public List<Customer> getCredits(){

        return  customerRepository.findAll();
    }

}
