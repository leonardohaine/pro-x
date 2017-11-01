package br.com.prox;

import javax.servlet.Filter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.orm.hibernate5.support.OpenSessionInViewFilter;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;

import br.com.prox.model.Empresa;
import br.com.prox.nfe.DownloadNFe;
import br.com.prox.nfe.job.ConsultaDistribuicaoXMLJob;
import br.com.prox.repository.Empresas;
import lombok.Data;

@EnableScheduling
@SpringBootApplication
public class ProXApplication {

	
	public static void main(String[] args) {
		SpringApplication.run(ProXApplication.class, args);	
	}
	
	@Bean
	public ThreadPoolTaskScheduler threadPoolTaskScheduler() {
        return new ThreadPoolTaskScheduler();
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
