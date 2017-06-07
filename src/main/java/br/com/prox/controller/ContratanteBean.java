package br.com.prox.controller;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ViewScoped;
import javax.inject.Named;

import org.omnifaces.util.Messages;
import org.springframework.beans.factory.annotation.Autowired;

import br.com.prox.model.Contratante;
import br.com.prox.repository.ContratanteDAO;
import br.com.prox.service.ContratanteService;
import lombok.Data;

//@ManagedBean
@Named
@ViewScoped
@Data
public class ContratanteBean {
	

	private Contratante contratante = new Contratante();

	private Contratante contratanteSelecionado;
	
	
	@Autowired
	private ContratanteDAO dao;
	
	@Autowired
	private ContratanteService service;
	
	private List<Contratante> contratantes;

	public void salvar() {

		try {

			service.salvar(contratante);

			Messages.addGlobalInfo("Contratante "+contratante.getNome() +" salvo com sucesso");
			
			listar();

			contratante = new Contratante();

			
			
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	
	@PostConstruct
	public void listar() {

		contratantes = dao.findAll();
	}

	
	public void excluir() {
		try {
			service.excluir(contratanteSelecionado);
			System.out.println("Contratante " + contratanteSelecionado.getNome() + " excluido com sucesso");
			contratanteSelecionado = null;

			Messages.addGlobalError("Ocorreu um erro ao salvar o contratante");
			
			listar();

		} catch (Exception e) {

			e.printStackTrace();

		}

	}
	
	
}
