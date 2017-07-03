package br.com.prox;

import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.web.servlet.ErrorPage;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.repository.support.DomainClassConverter;
import org.springframework.format.support.FormattingConversionService;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class WebConfig extends WebMvcConfigurerAdapter {

	@Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/javax.faces.resource/**").addResourceLocations("/javax.faces.resource/");
    }
	
	@Bean
	public EmbeddedServletContainerCustomizer containerCustomizer() {
		
		return (container -> {
			ErrorPage error401Page = new ErrorPage(HttpStatus.UNAUTHORIZED, "/error/403.jsf");
	        ErrorPage error403Page = new ErrorPage(HttpStatus.FORBIDDEN, "/error/403.jsf");
	        ErrorPage error404Page = new ErrorPage(HttpStatus.NOT_FOUND, "/error/404.jsf");
	        ErrorPage error500Page = new ErrorPage(HttpStatus.INTERNAL_SERVER_ERROR, "/error/500.jsf");
	 
	        container.addErrorPages(error401Page, error403Page, error404Page, error500Page);
	   });
		
//		return (container ->
//		container.addErrorPages(new ErrorPage(HttpStatus.NOT_FOUND, "/error/404.jsf"),
//							new ErrorPage(HttpStatus.FORBIDDEN, "/error/403.jsf"),
//							new ErrorPage(HttpStatus.INTERNAL_SERVER_ERROR, "/error/500.jsf")));
	}
	
	@Bean
	public DomainClassConverter<FormattingConversionService> domainClassConverter(
			FormattingConversionService conversionService) {
		return new DomainClassConverter<FormattingConversionService>(conversionService);
	}
	
//	@Bean
//    public ViewResolver getViewResolver() {
//        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
//        resolver.setPrefix("/WEB-INF/");
//        resolver.setSuffix(".xhtml");
//        return resolver;
//    }

//    @Override
//    public void configureDefaultServletHandling(
//            DefaultServletHandlerConfigurer configurer) {
//        configurer.enable();
//    }    
	
//	@Bean
//	public SpringTemplateEngine templateEngine(TemplateResolver templateResolver) {
//	SpringTemplateEngine templateEngine = new SpringTemplateEngine();
//	templateEngine.setTemplateResolver(templateResolver);
//	templateEngine.addDialect(new SpringSecurityDialect());
//	return templateEngine;
//	}
	
	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addRedirectViewController("/", "/login.jsf");
		registry.addRedirectViewController("", "/login.jsf");
	}
	
	
	
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
		return bCryptPasswordEncoder;
	}
	
}
