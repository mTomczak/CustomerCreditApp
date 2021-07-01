package app.controler;

import app.SpringRestBootApplicationCredit;
import app.Wraper.CustomerWraper;
import app.Wraper.ProductWraper;
import app.model.Credit;
import app.model.Customer;
import app.model.Product;
import app.model.RestCreditModel;
import app.repository.CreditRepository;
import app.dao.CreditDaoImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.List;


/**
 * The class that controls credit events. Establishing a loan, downloading information about the loan.
 */

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
    CustomerWraper customerWraper;

    @Autowired
    ProductWraper productWraper;



    private RestTemplate restTemplate = new RestTemplate();

    @Autowired
    public CreditController(CreditDaoImpl creditDaoimpl, RestCreditModel restCreditModel, CreditRepository creditRepository) {
        this.creditDaoImpl = creditDaoimpl;
        this.restCreditModel = restCreditModel;
        this.creditRepository = creditRepository;
    }

    /**
     * A method to check if the website is working.
     * @return endpoint returning the string "Credit module is working"
     */
    @RequestMapping("/isitworking")
    public String test() {
        return "Credit module is working";
    }


    /**
     * Credit Creation Method
     * It accepts credit and product data as parameters
     *
     * @param userFirstName userFirstName
     * @param userSurname userSurname
     * @param personalNumber personalNumber
     * @param productName productName
     * @param productValue productValue
     * @param creditName creditName
     * @return Returns an object with collected information about a complex loan.
     */
    @RequestMapping("/createcredit")
    @ResponseStatus(HttpStatus.CREATED)
    public RestCreditModel createcredit(
            @RequestParam(defaultValue = "nieznany") String userFirstName,
            @RequestParam(defaultValue = "nieznany") String userSurname,
            @RequestParam(defaultValue = "nieznany") String personalNumber,
            @RequestParam(defaultValue = "nieznany") String productName,
            @RequestParam(defaultValue = "00000000") long productValue,
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


        restTemplate.postForEntity("http://localhost:8082/createCustomer", customer, Customer.class, Collections.EMPTY_MAP);

        logger.info("Credit - Zapisany customer do bazy");

        try{
            customer =  restTemplate.getForObject("http://localhost:8082/getcustomer/"+customer.getPesel(), Customer.class);
            restCreditModel.setUserID(customer.getID());
        }catch (NullPointerException e ){

        }
        credit.setCustomerID(customer.getID());

        logger.info("Pobrany customer z bazy " + customer.getFirstName());

        product.setProductName(restCreditModel.getProductName());
        product.setValue(restCreditModel.getProductValue());


        restTemplate.postForEntity("http://localhost:8081/createProduct", product, Product.class, Collections.EMPTY_MAP);

        try{
            product = restTemplate.getForObject("http://localhost:8081/getProduct/"+product.getProductName(), Product.class);
            restCreditModel.setProductID(product.getID());
        }catch (NullPointerException e){

        }
        credit.setProudctID(product.getID());

        creditRepository.save(credit);

        return restCreditModel;

    }


    /**
     * A method that collects data on all loans.
     * @return A list of loans with information about each of them. Each item on the list contains information about the customer and the credit with the product
     */

    @RequestMapping("/getcredits")
    @ResponseStatus(HttpStatus.GONE)
    public List<RestCreditModel> getCredits() {

        List<Credit> creditList = creditRepository.findAll();

        customerWraper = restTemplate.getForObject("http://localhost:8082/getallcustomer/", CustomerWraper.class);

        productWraper = restTemplate.getForObject("http://localhost:8081/getallproduct/", ProductWraper.class);

        List<RestCreditModel> finallCreditList = creditDaoImpl.getAllCredits(creditList, productWraper.getProductList(), customerWraper.getCustomerList());


        return finallCreditList;
    }
}
