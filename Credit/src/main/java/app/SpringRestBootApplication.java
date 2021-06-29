package app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class SpringRestBootApplication {

    public static void main(String[] args) throws InterruptedException {

        SpringApplication.run(SpringRestBootApplication.class);
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