package app.controler;

import app.dao.CreditDao;
import app.dao.CreditDaoImpl;
import app.model.Credit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Controller
@RestController
public class CreditController {


    private CreditDaoImpl creditDao;
    @Autowired
    public CreditController(CreditDaoImpl creditDaoimpl){
        this.creditDao = creditDaoimpl;
    }

    @RequestMapping("/isitworking")
    public String test(){
        return "isitworking";
    }

    @RequestMapping("/")
    public String home(){
        return "home";
    }


    @RequestMapping("/createcredit")
    public void createcredit(@RequestParam(defaultValue = "nieznany") String creditName){
        creditDao.saveCredit(new Credit(creditName));


    }



    @RequestMapping("/getcredits")
    public String getCredits(){

        List<Credit> creditList = creditDao.getCredits();

        System.out.println(creditList.size());


        if (creditList.size() != 0) {

            return creditDao.getCredits().toString();
        }
        else

            return "{Nie ma kredytów}";
    }

}
