package br.com.prox.controller;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.prox.model.Consultor;
import br.com.prox.service.ConsultorService;
import lombok.Data;

@ManagedBean
@ViewScoped
@Data
public class ConsultorBean {
	
	private ConsultorService service;
	
	private Consultor consultor;
	
	
	public void salvar() {
		try {

			service.salvar(consultor);

		} catch (Exception e) {
			e.printStackTrace();

		}

	}

	
	
	
	
}
