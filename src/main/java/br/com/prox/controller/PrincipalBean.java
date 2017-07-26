package br.com.prox.controller;

import java.io.IOException;
import java.time.LocalDateTime;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.HttpSession;

import org.springframework.security.core.context.SecurityContextHolder;

import lombok.Data;

@Named
@SessionScoped
@Data
public class PrincipalBean {
	
	private String usuarioLogado;
	private Boolean isAutenticado = false;
	
	@PostConstruct
	public void logando(){
		try{
			
			if(SecurityContextHolder.getContext().getAuthentication() != null){
				System.out.println("" + SecurityContextHolder.getContext().getAuthentication().getPrincipal());
				System.out.println("" + SecurityContextHolder.getContext().getAuthentication().getDetails());
				usuarioLogado = SecurityContextHolder.getContext().getAuthentication().getName().toUpperCase();
				System.out.println("Usuário logado: " + usuarioLogado + " - Acessos: " + SecurityContextHolder.getContext().getAuthentication().getAuthorities());
				
			}
		}catch (Exception e) {
			usuarioLogado = "Anônimo";
			System.out.println("Usuário = " + usuarioLogado);
			e.printStackTrace();
			//throw new RuntimeException("Usuário anônimo!");
		}
		
	}
	
	public void logout() throws IOException{
		System.out.println("Autenticado? = " + isAutenticado);
		isAutenticado = true;
		System.out.println("Autenticado? = " + isAutenticado);
		System.out.println("Data e hora = " + LocalDateTime.now());
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, 
                "time out.", "Sessão expirou após 30 minutos de inatividade"));
		System.out.println("redirecionando para login.");
		FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
		HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
		
		//FacesContext.getCurrentInstance().getExternalContext().redirect("login.xhtml");
		
	}

}
