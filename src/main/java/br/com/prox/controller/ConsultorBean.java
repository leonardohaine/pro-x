package br.com.prox.controller;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

import javax.faces.bean.ViewScoped;
import javax.inject.Named;

import org.primefaces.context.RequestContext;
import org.springframework.beans.factory.annotation.Autowired;

import br.com.prox.model.Consultor;
import br.com.prox.model.Projeto;
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
	
	
	public void prepararNovoCadastro() {
		consultor = new Consultor();
	}
	
	public void salvar() {
		try {
			service.salvar(consultor);
			consultar();
			RequestContext.getCurrentInstance().update(
					Arrays.asList("frm:msgs", "frm:consultor-table"));
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void excluir(){
		
		service.excluir(consultorSelecionado);
		consultorSelecionado = null;
		
		consultar();
	}
	
	public void consultar(){
		todosConsultores = consultores.findAll();
	}
	
	public Consultor getConsultor(Long id) {
        if (id == null){
            throw new IllegalArgumentException("no id provided");
        }
    	
        for (Consultor con : todosConsultores){
            if (id.equals(con.getId())){
                return con;
            }
        }
        return null;
    }
}
