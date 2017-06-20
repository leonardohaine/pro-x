package br.com.prox;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	/*@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Autowired
	private DataSource datasource;
	
	@Value("${spring.queries.users-query}")
	private String usersQuery;
	
	@Value("${spring.queries.roles-query}")
	private String rolesQuery;*/
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		
		/*auth.
		jdbcAuthentication()
			.usersByUsernameQuery(usersQuery)
			.authoritiesByUsernameQuery(rolesQuery)
			.dataSource(datasource)
			.passwordEncoder(bCryptPasswordEncoder)
			;*/
		 
		//System.out.println("AUTHENTICATION DETAILS: " + authentication.getDetails());
		auth.inMemoryAuthentication()
			.withUser("haine").password("leonardo").authorities("CONSULTOR", "PROJETO", "APONTAMENTO", "CONTRATANTE").and()
			.withUser("silva").password("diego").authorities("CONSULTOR", "PROJETO", "APONTAMENTO", "CONTRATANTE");
	}

	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring()
			.antMatchers("/javax.faces.resource/**")
			.antMatchers("/templates/**");
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable();
		http
		  .authorizeRequests()
	          .antMatchers("/javax.faces.resource/**").permitAll()
	          .antMatchers("/error/404.jsf", "/error/500.jsf", "/error/403.jsf").permitAll() // Precisa estar autenticado
	          .antMatchers("/princpal.jsf").fullyAuthenticated()
	          .antMatchers("/consultor.jsf").hasAuthority("CONSULTOR")
	          .antMatchers("/projeto.jsf").hasAuthority("PROJETO")
	          .antMatchers("/apontamento.jsf").hasAuthority("APONTAMENTO")
	          .antMatchers("/contratante.jsf").hasAuthority("CONTRATANTE")
				.and()
			.formLogin()
			.loginPage("/login.xhtml")
			.loginProcessingUrl("/login")
			.defaultSuccessUrl("/principal.xhtml")
			.and()
			.logout().logoutUrl("/logout")
			.logoutSuccessUrl("/login.xhtml")
			.invalidateHttpSession(true)
			
			/*		.loginPage("/index.jsf")
				 .failureUrl("/index.jsf?invalid=true")
				.permitAll()
				.and()
			.logout()
				.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
				 .and()
			     .exceptionHandling().accessDeniedPage("/error/403.jsf")
				.and()
				.rememberMe()
				.tokenRepository(persistentTokenRepository())
				.key("rem-me-key")
				.rememberMeParameter("remember-me")
				.rememberMeCookieName("my-remember-me")
				.tokenValiditySeconds(86400)*/
				; 
	}
	
	
//	@Bean
//	public PersistentTokenRepository persistentTokenRepository() {
//		JdbcTokenRepositoryImpl tokenRepository = new JdbcTokenRepositoryImpl();
//		//tokenRepository.setDataSource(datasource);
//		return tokenRepository;
//	}
}
