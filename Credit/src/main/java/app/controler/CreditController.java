package app.controler;

import app.dao.CreditDaoImpl;
import app.model.Credit;
import app.model.RestCreditModel;
import app.repository.CreditRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@Controller
@RestController
public class CreditController {


    private CreditDaoImpl creditDaoImpl;
    private RestCreditModel restCreditModel;
    private CreditRepository creditRepository;



    @Autowired
    public CreditController(CreditDaoImpl creditDaoimpl, RestCreditModel restCreditModel, CreditRepository creditRepository){
        this.creditDaoImpl = creditDaoimpl;
        this.restCreditModel = restCreditModel;
        this.creditRepository = creditRepository;
    }

    @RequestMapping("/isitworking")
    public String test(){
        return "isitworking";
    }

    @RequestMapping("/")
    public String home(){
        return "home";
    }

    @RequestMapping("/repository")
    public void repository(){
        List<Credit> creditList = new ArrayList<>();
        creditList.add(new Credit("jedyny"));
        creditList.add(new Credit("zielony"));
        creditList.add(new Credit("fajny"));

        creditList.forEach(creditRepository::save);


    }


    @RequestMapping("/createcredit")
    @ResponseStatus(HttpStatus.CREATED)
    public void createcredit(
            @RequestParam(defaultValue = "nieznany") String userFirstName,
            @RequestParam(defaultValue = "nieznany") String userSurname,
            @RequestParam(defaultValue = "nieznany") String personalNumber,
            @RequestParam(defaultValue = "nieznany") String productName,
            @RequestParam(defaultValue = "nieznany") long productValue,
            @RequestParam(defaultValue = "nieznany") String creditName
            ){

        restCreditModel.setCreditName(userFirstName);
        restCreditModel.setUserSurname(userSurname);
        restCreditModel.setPersonalNumber(personalNumber);
        restCreditModel.setProductName(productName);
        restCreditModel.setProductValue(productValue);
        restCreditModel.setCreditName(creditName);



    }



    @RequestMapping("/getcredits")
    @ResponseStatus(HttpStatus.GONE)
    public List<Credit> getCredits(){

        List<Credit> creditList = creditDaoImpl.getCredits();

        if (creditList.size() != 0) {

            return creditList;
        }
        else

            return null;
    }

}
