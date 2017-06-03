package br.com.prox.controller;

import java.io.Serializable;
import java.sql.Time;
import java.util.Arrays;
import java.util.List;

import javax.faces.bean.ViewScoped;
import javax.inject.Named;

import org.primefaces.context.RequestContext;
import org.springframework.beans.factory.annotation.Autowired;

import br.com.prox.model.Apontamento;
import br.com.prox.repository.ApontamentoDAO;
import br.com.prox.service.ApontamentoService;
import lombok.Data;

@Named
@ViewScoped
@Data
public class ApontamentoBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Autowired
	private ApontamentoService service;
	
	@Autowired
	private ApontamentoDAO apontamentos;
	
	private Apontamento apontamento = new Apontamento();
	private Apontamento apontamentoSelecionado;
	private List<Apontamento> todosApontamentos;
	
	
	public void prepararNovoCadastro() {
		apontamento = new Apontamento();
	}
	
	public void salvar() {
		try {
			//apontamento.setTempoGasto(new Time(apontamento.getTempoGasto()));
			
			service.salvar(apontamento);
			consultar();
			RequestContext.getCurrentInstance().update(
					Arrays.asList("frm:msgs", "frm:apontamento-table"));
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void excluir(){
		
		service.excluir(apontamentoSelecionado);
		apontamentoSelecionado = null;
		
		consultar();
	}
	
	public void consultar(){
		todosApontamentos = apontamentos.findAll();
	}
	
	
}
