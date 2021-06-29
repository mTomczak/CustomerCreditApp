package app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;


@SpringBootApplication
public class SpringMvcBootApplication  {

    public static void main(String[] args) throws InterruptedException {

        SpringApplication.run(SpringMvcBootApplication.class);
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