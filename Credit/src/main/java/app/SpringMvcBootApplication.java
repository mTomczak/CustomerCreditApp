package app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;


@SpringBootApplication
public class SpringMvcBootApplication {

    public static void main(String[] args) throws InterruptedException {
        System.out.println("test aplikacji");

        ConfigurableApplicationContext contextApp = SpringApplication.run(SpringMvcBootApplication.class, args);



//        CreditDao creditDao = new CreditDaoImpl();
//
//        Credit credit = new Credit();
//        credit.setCreditName("Sekend");
//
//        creditDao.saveCredit(credit);
//
//        System.out.println("wstawiony credit");

    }
}
