package org.cool.zoo.configure;

import org.apache.commons.dbcp.BasicDataSource;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpHeaders;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Properties;

/**
 * Created by Dang Dim
 * Date     : 10-Jan-18, 8:47 AM
 * Email    : d.dim@gl-f.com
 */

@Configuration
@EnableTransactionManagement
@PropertySource(value = {"classpath:/config/database.properties", "classpath:/config/hibernate.properties"})
public class WebConfiguration {

    @Autowired
    private Environment environment;

    @Bean
    public DataSource getDataSource() {
        BasicDataSource ds = new BasicDataSource();
        ds.setDriverClassName(environment.getProperty("cool.db.driver"));
        ds.setUrl(environment.getProperty("cool.db.url"));
        ds.setUsername(environment.getProperty("cool.db.username"));
        ds.setPassword(environment.getProperty("cool.db.password"));
        ds.setValidationQuery(environment.getProperty("cool.datasource.validationQuery"));
        ds.setTestWhileIdle(Boolean.parseBoolean(environment.getProperty("cool.datasource.testWhileIdle")));
        return ds;
    }

    @Bean
    public HttpHeaders httpHeadersFCM() {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set("Authorization", "key=" + environment.getProperty("fcm.server.key"));
        httpHeaders.set("Content-Type", "application/json");
        return httpHeaders;
    }

    @Bean
    public String fcmUrl() {
        return environment.getProperty("fcm.url");
    }

    public Properties getHibernateProperties() {
        Properties hibernateProperties = new Properties();
        hibernateProperties.put("hibernate.dialect", environment.getProperty("cool.hibernate.dialect"));
        hibernateProperties.put("hibernate.hbm2ddl.auto", environment.getProperty("cool.hibernate.hbm2ddl.auto"));
        hibernateProperties.put("hibernate.show_sql", environment.getProperty("cool.hibernate.show_sql"));
        hibernateProperties.put("hibernate.enable_lazy_load_no_trans", true);
        return hibernateProperties;
    }
}
