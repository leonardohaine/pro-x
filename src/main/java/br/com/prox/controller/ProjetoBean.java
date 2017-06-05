package br.com.prox.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ViewScoped;
import javax.inject.Named;

import org.primefaces.context.RequestContext;
import org.primefaces.model.DualListModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import br.com.prox.model.Consultor;
import br.com.prox.model.Projeto;
import br.com.prox.repository.ConsultorDAO;
import br.com.prox.repository.ProjetoDAO;
import br.com.prox.service.ProjetoService;
import br.com.prox.util.FacesMessages;
import lombok.Data;

@Named
@ViewScoped
@Data
//@ManagedBean
@Transactional
public class ProjetoBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Autowired
	private ProjetoService service;
	
	@Autowired
	private ProjetoDAO projetos;
	
	@Autowired
	private ConsultorDAO consultores;
	
	@Autowired
	private FacesMessages messages;
	
	private Projeto projeto = new Projeto();
	private Projeto projetoSelecionado;
	private List<Projeto> todosProjetos;
	
	private List<Consultor> listaConsultores = new ArrayList();
	
	private DualListModel<Consultor> consultorModel;
	
	
	public DualListModel<Consultor> getConsultorModel(){
		// if(this.roleModel==null){ 
			 if(projeto.getConsultor() != null){
				 this.consultorModel = new DualListModel<Consultor>(new ArrayList<>(projeto.getConsultor()), new ArrayList<Consultor>(projeto.getConsultor()));
			 }else{
				 this.consultorModel = new DualListModel<Consultor>(new ArrayList<>(listaConsultores), new ArrayList<Consultor>());
			 }
		// }
		 return this.consultorModel;
	}
	
	public void prepararNovoCadastro() {
		projeto = new Projeto();
	}
	
	public void salvar() {
		try {
			projeto.setConsultor(consultorModel.getTarget());
			
			service.salvar(projeto);
			consultar();
			messages.info("Projeto salvo com sucesso!");
			RequestContext.getCurrentInstance().update(
					Arrays.asList("frm:msgs", "frm:projeto-table"));
		} catch (Exception e) {
			e.printStackTrace();
			messages.error("Erro ao tentar salvar projeto: \n" + e);
			RequestContext.getCurrentInstance().update("frm:msgs");
		}

	}

	public void excluir(){
		
		service.excluir(projetoSelecionado);
		projetoSelecionado = null;
		consultar();
		messages.info("Projeto excluído com sucesso!");
	}
	
	public void consultar(){
		todosProjetos = projetos.findAll();
	}
	
	@PostConstruct
	public void consultarConsultores(){
		listaConsultores = consultores.findAll();
	}
	
}
