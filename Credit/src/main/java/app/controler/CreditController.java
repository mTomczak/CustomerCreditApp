package app.controler;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CreditController {

    @RequestMapping("/")
    public String home(){
        return "home";
    }

}
