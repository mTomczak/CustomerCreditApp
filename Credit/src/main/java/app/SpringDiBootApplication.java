package app;

import dao.CreditDao;
import dao.CreditDaoImpl;
import model.Credit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;


@SpringBootApplication
public class SpringDiBootApplication {

    public static void main(String[] args) throws InterruptedException {
        System.out.println("test aplikacji");

        ConfigurableApplicationContext contextApp = SpringApplication.run(SpringDiBootApplication.class, args);
//        SpringApplication.run(SpringDiBootApplication.class, args);
//        AnnotationConfigApplicationContext contextApp =
//                new AnnotationConfigApplicationContext(SpringDiBootApplication.class);

//        CreditDaoImpl creditDao = contextApp.getBean(CreditDaoImpl.class);

        CreditDao creditDao = new CreditDaoImpl();

        Credit credit = new Credit();
        credit.setCreditName("FirstCredit");

        creditDao.saveCredit(credit);

        System.out.println("wstawiony credit");

    }
}
