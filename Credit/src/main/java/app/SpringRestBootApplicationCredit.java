package app;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class SpringRestBootApplicationCredit {

    public static void main(String[] args) throws InterruptedException {

        SpringApplication.run(SpringRestBootApplicationCredit.class);


//        ConfigurableApplicationContext contextApp = SpringApplication.run(SpringMvcBootApplication.class, args);

    }
//
//    @Override
//    protected Class<?>[] getRootConfigClasses() {
//        return null;
//    }
//
//    @Override
//    protected Class<?>[] getServletConfigClasses() {
//        return new Class[]{WebConfig.class};
//    }
//
//    @Override
//    protected String[] getServletMappings() {
//        return new String[]{"/"};
//    }
}

//extends AbstractAnnotationConfigDispatcherServletInitializer