package app;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories
public class DbConfiguration {

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(JpaVendorAdapter adapter, DataSource ds) {
        LocalContainerEntityManagerFactoryBean emf = new LocalContainerEntityManagerFactoryBean();
        emf.setPersistenceUnitName("persistence");
        Map<String, String> properties = new HashMap<>();
        properties.put("javax.persistence.schema-generation.database.action", "drop-and-create");
        emf.setJpaPropertyMap(properties);
        emf.setDataSource(ds);
        emf.setJpaVendorAdapter(adapter);
        emf.setPackagesToScan("app");
        return emf;
    }

    @Bean
    public JpaVendorAdapter createVendorAdapter() {
        HibernateJpaVendorAdapter adapter = new HibernateJpaVendorAdapter();
        adapter.setDatabase(Database.MYSQL);
        adapter.setGenerateDdl(true);
        adapter.setShowSql(true);
        return adapter;
    }

    @Bean
    public DataSource createDS() {
        BasicDataSource ds = new BasicDataSource();
        ds.setUrl("jdbc:mysql://localhost:3306/creditapp?useSSL=false");
        ds.setUsername("credituser");
        ds.setPassword("ork0t!QA");
        ds.setDriverClassName("com.mysql.cj.jdbc.Driver");
        ds.setInitialSize(5);
        return ds;
    }

    @Bean
    public PlatformTransactionManager transactionManager(EntityManagerFactory emf) {
        JpaTransactionManager txManager = new JpaTransactionManager(emf);
        return txManager;
    }

}
