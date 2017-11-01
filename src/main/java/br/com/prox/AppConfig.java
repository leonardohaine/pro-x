package br.com.prox;

import javax.persistence.EntityManagerFactory;
import javax.servlet.Filter;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate4.support.OpenSessionInViewFilter;
import org.springframework.orm.jpa.vendor.HibernateJpaSessionFactoryBean;
import org.springframework.web.filter.HiddenHttpMethodFilter;  

@Configuration
@ComponentScan
@EnableAutoConfiguration
public class AppConfig {

    @Bean
    public FilterRegistrationBean filterRegistration() {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(openSessionInView());
        registration.addUrlPatterns("/*");

        return registration;
    }

    @Bean
    public HibernateJpaSessionFactoryBean sessionFactory(EntityManagerFactory emf) {
        HibernateJpaSessionFactoryBean fact = new HibernateJpaSessionFactoryBean();
        fact.setEntityManagerFactory(emf);
        return fact;
    }
    
    /*@Bean
    public FilterRegistrationBean FileUploadFilter() {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(new org.primefaces.webapp.filter.FileUploadFilter());
        registration.setName("PrimeFaces FileUpload Filter");
        return registration;
    }
    
    @Bean
    public FilterRegistrationBean hiddenHttpMethodFilterDisabled(
            @Qualifier("hiddenHttpMethodFilter") HiddenHttpMethodFilter filter) { 
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean(filter);
        filterRegistrationBean.setEnabled(false);
        return filterRegistrationBean;
    }*/
    
    @Bean
    public Filter openSessionInView() {
        return new OpenSessionInViewFilter();
    }
}