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
	private FacesMessages facesMessages;
	
	private Projeto projeto = new Projeto();
	private Projeto projetoSelecionado;
	private List<Projeto> todosProjetos;
	
	private List<Consultor> listaConsultores;
	
	private DualListModel<Consultor> consultorModel;
	
	
	public DualListModel<Consultor> getConsultorModel(){
		// if(this.roleModel==null){ 
			 if(projeto.getConsultor() != null){
				 this.consultorModel = new DualListModel<Consultor>(new ArrayList<>(listaConsultores), new ArrayList<Consultor>(projeto.getConsultor()));
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
			RequestContext.getCurrentInstance().update(
					Arrays.asList("frm:msgs", "frm:projeto-table"));
		} catch (Exception e) {
			e.printStackTrace();
			facesMessages.info("Erro ao salvar projeto: \n" + e);
		}

	}

	public void excluir(){
		
		service.excluir(projetoSelecionado);
		projetoSelecionado = null;
		
		consultar();
	}
	
	public void consultar(){
		todosProjetos = projetos.findAll();
	}
	
	@PostConstruct
	public void consultarConsultores(){
		listaConsultores = consultores.findAll();
	}

	public Projeto getProjeto(Long id) {
        if (id == null){
            throw new IllegalArgumentException("no id provided");
        }
        
        for (Projeto proj : todosProjetos){
            if (id.equals(proj.getId())){
                return proj;
            }
        }
        return null;
    }
	
}
