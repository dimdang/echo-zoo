package org.cool.zoo.configure;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.vendor.HibernateJpaSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * Created by Dang Dim
 * Date     : 18-Jan-18, 11:38 AM
 * Email    : d.dim@gl-f.com
 */

@Configuration
@EnableTransactionManagement
@ComponentScan("org.cool")
public class HibernateConfiguration {

    @Bean
    public HibernateJpaSessionFactoryBean sessionFactory() {
        return new HibernateJpaSessionFactoryBean();
    }

}
