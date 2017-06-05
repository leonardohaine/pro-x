package br.com.prox.service;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.prox.filtro.FiltroApontamento;
import br.com.prox.model.Apontamento;
import br.com.prox.repository.ApontamentoDAO;

@Service
public class ApontamentoService implements Serializable {
    private static final long serialVersionUID = 1L;
	
	@Autowired
	private ApontamentoDAO dao;
	
	public void salvar(Apontamento apontamento){
		dao.save(apontamento);
	}

	public void excluir(Apontamento apontamento) {
		dao.delete(apontamento);
	}

	public List<Apontamento> getApontamentoFiltro(FiltroApontamento filtro){
		
		if(filtro.getProjeto() != null && filtro.getAtividade() !=  null){
			return dao.findByProjetoAndAtividade(filtro.getProjeto(), filtro.getAtividade());
		}else if(filtro.getProjeto() == null && filtro.getAtividade() != null){
			return dao.findByAtividade(filtro.getAtividade());
		}else if(filtro.getProjeto() != null && filtro.getAtividade() == null){
			return dao.findByProjeto(filtro.getProjeto());
		}else{
			return dao.findAll();
		}
	}
	
	
}
