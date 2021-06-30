package app.Controller;

import app.repository.CustomerRepository;
import app.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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


    @RequestMapping(value = "/createCustomer", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    public void createCustomer(
            @RequestBody() Customer customer){
        System.out.println("Customer - podany customer: " + customer.getPesel());
        this.customer = customer;

       try {
           System.out.println("Customer - znaleziony w bazie : " + customerRepository.findByPesel(customer.getPesel()).getPesel());
       }catch (NullPointerException e){

       }

           if(customerRepository.findByPesel(customer.getPesel()) == null) {
               customerRepository.save(customer);
               System.out.println("Customer - zapisany do bazy");

           }
    }
    @RequestMapping("/getCustomer/{pesel}")
    @ResponseStatus(HttpStatus.OK)
    public Customer getCustomer(@PathVariable(value = "pesel") String pesel){
        return  customerRepository.findByPesel(pesel);

    }



    @RequestMapping("/getAllCustomer")
    @ResponseStatus(HttpStatus.GONE)
    public List<Customer> getCredits(){

        return  customerRepository.findAll();
    }

}
