package app.controler;

import app.SpringRestBootApplicationCredit;
import app.model.Credit;
import app.model.Customer;
import app.model.RestCreditModel;
import app.repository.CreditRepository;
import app.dao.CreditDaoImpl;
import app.model.Product;
import com.fasterxml.jackson.core.type.TypeReference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.List;

@Controller
@RestController
public class CreditController {


    private CreditDaoImpl creditDaoImpl;
    private RestCreditModel restCreditModel;
    private CreditRepository creditRepository;
    private Logger logger = LoggerFactory.getLogger(SpringRestBootApplicationCredit.class);

    @Autowired
    Credit credit;

    @Autowired
    Customer customer;

    @Autowired
    Product product;

    @Autowired
    public CreditController(CreditDaoImpl creditDaoimpl, RestCreditModel restCreditModel, CreditRepository creditRepository) {
        this.creditDaoImpl = creditDaoimpl;
        this.restCreditModel = restCreditModel;
        this.creditRepository = creditRepository;
    }

    @RequestMapping("/isitworking")
    public String test() {
        return "isitworking";
    }


    @RequestMapping("/createcredit")
    @ResponseStatus(HttpStatus.CREATED)
    public String createcredit(
            @RequestParam(defaultValue = "nieznany") String userFirstName,
            @RequestParam(defaultValue = "nieznany") String userSurname,
            @RequestParam(defaultValue = "nieznany") String personalNumber,
            @RequestParam(defaultValue = "nieznany") String productName,
            @RequestParam(defaultValue = "nieznany") long productValue,
            @RequestParam(defaultValue = "nieznany") String creditName
    ) {

        restCreditModel.setUserFirstName(userFirstName);
        restCreditModel.setUserSurname(userSurname);
        restCreditModel.setPersonalNumber(personalNumber);
        restCreditModel.setProductName(productName);
        restCreditModel.setProductValue(productValue);
        restCreditModel.setCreditName(creditName);

        logger.info("Credit - restCreditModel name = " + restCreditModel.getCreditName());
        logger.info("Credit - restCreditModel person " + restCreditModel.getUserSurname());


        credit.setCreditName(restCreditModel.getCreditName());
        creditRepository.save(credit);

        restCreditModel.setCreditID(creditRepository.findByCreditName(credit.getCreditName()).getID());

        logger.info("Credit - Id zapisanego kredytu = " + restCreditModel.getCreditID());

        customer.setFirstName(restCreditModel.getUserFirstName());
        customer.setSurname(restCreditModel.getUserSurname());
        customer.setPesel(restCreditModel.getPersonalNumber());

        logger.info("Credit - Stworzony customer = " + customer.getPesel());

        RestTemplate restTemplate = new RestTemplate();
        restTemplate.postForEntity("http://localhost:8082/createCustomer", customer, Customer.class, Collections.EMPTY_MAP);

        logger.info("Credit - Zapisany customer do bazy");

        try{
            customer =  restTemplate.getForObject("http://localhost:8082/getCustomer/"+customer.getPesel(), Customer.class);
            restCreditModel.setUserID(customer.getID());
        }catch (NullPointerException e ){

        }

        logger.info("Pobrany customer z bazy " + customer.getFirstName());

        product.setProductName(restCreditModel.getProductName());
        product.setValue(restCreditModel.getProductValue());


        restTemplate.postForEntity("http://localhost:8081/createProduct", product, Product.class, Collections.EMPTY_MAP);

        try{
            product = restTemplate.getForObject("http://localhost:8081/getProduct/"+product.getProductName(), Product.class);
            restCreditModel.setProductID(product.getID());
        }catch (NullPointerException e){

        }


        return restCreditModel.toString();

    }


    @RequestMapping("/getcredits")
    @ResponseStatus(HttpStatus.GONE)
    public List<Credit> getCredits() {

        List<Credit> creditList = creditDaoImpl.getCredits();

        if (creditList.size() != 0) {

            return creditList;
        } else

            return null;
    }

}
