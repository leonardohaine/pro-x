package br.com.prox;

import javax.servlet.Filter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.orm.hibernate5.support.OpenSessionInViewFilter;

@SpringBootApplication
public class ProXApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProXApplication.class, args);
	}
	
	@Bean
    public FilterRegistrationBean filterRegistration() {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(openSessionInView());
        registration.addUrlPatterns("/*");

        return registration;
    }

    @Bean
    public Filter openSessionInView() {
        return new OpenSessionInViewFilter();
    }
	
}
