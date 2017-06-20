package br.com.prox.controller;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import org.springframework.security.core.context.SecurityContextHolder;

import lombok.Data;

@Named
@SessionScoped
@Data
public class PrincipalBean {
	
	private String usuarioLogado;
	
	@PostConstruct
	public void usuarioLogado(){
		try{
			usuarioLogado = SecurityContextHolder.getContext().getAuthentication().getName().toUpperCase();
			System.out.println(usuarioLogado);
		}catch (Exception e) {
			usuarioLogado = "Anônimo";
			System.out.println("Usuário anônimo.");
			//throw new RuntimeException("Usuário anônimo!");
		}
	}

}
