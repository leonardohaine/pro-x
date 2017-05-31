package br.com.prox.controller;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Named;

import org.primefaces.context.RequestContext;
import org.springframework.beans.factory.annotation.Autowired;

import br.com.prox.model.Consultor;
import br.com.prox.repository.ConsultorDAO;
import br.com.prox.service.ConsultorService;
import lombok.Data;

@Named
@ViewScoped
@Data
public class ConsultorBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Autowired
	private ConsultorService service;
	
	@Autowired
	private ConsultorDAO consultores;
	
	private Consultor consultor = new Consultor();
	private Consultor consultorSelecionado;
	private List<Consultor> todosConsultores;
	
	public void salvar() {
		try {
			service.salvar(consultor);
			RequestContext.getCurrentInstance().update(
					Arrays.asList("frm:msgs", "frm:consultor-table"));
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void excluir(){
		service.excluir(consultor);
		RequestContext.getCurrentInstance().update(
				Arrays.asList("frm:msgs", "frm:consultor-table"));
	}
	
	public void consultar(){
		todosConsultores = consultores.findAll();
	}
	
	
}
