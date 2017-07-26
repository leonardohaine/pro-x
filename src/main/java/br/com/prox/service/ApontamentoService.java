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
	
	public void atualizarStatus(Apontamento apontamento) {
		dao.saveAndFlush(apontamento);
	}

	public List<Apontamento> filtroApontamento(FiltroApontamento filtro){
		
		if(filtro.getProjeto() != null && filtro.getAtividade() !=  null){
			
			System.out.println("Pesquisando projeto e atividade...");
			return dao.findByProjetoAndAtividade(filtro.getProjeto(), filtro.getAtividade());
			
		}else if(filtro.getProjeto() == null && filtro.getAtividade() != null && filtro.getDataInicial() != null && filtro.getDataFinal() != null){
			
			System.out.println("Pesquisando atividade e datas...");
			return dao.findByAtividadeAndDataBetween(filtro.getAtividade(), filtro.getDataInicial(), filtro.getDataFinal());	
			
		}else if(filtro.getProjeto() == null && filtro.getAtividade() != null){
			
			System.out.println("Pesquisando atividade...");
			return dao.findByAtividade(filtro.getAtividade());
			
		}else if(filtro.getDataInicial() != null && filtro.getDataFinal() != null &&filtro.getProjeto() != null && filtro.getAtividade() == null){
			
			System.out.println("Pesquisando por projeto e data...");
			return dao.findByProjetoAndDataBetween(filtro.getProjeto(), filtro.getDataInicial(), filtro.getDataFinal());
			
		}else if(filtro.getDataInicial() != null && filtro.getDataFinal() != null){
			
			System.out.println("Pesquisando por data...");
			return dao.findByDataBetween(filtro.getDataInicial(), filtro.getDataFinal());
			
		}else if(filtro.getDataInicial() != null && filtro.getDataFinal() != null){
			
			System.out.println("Pesquisando por datas...");
			return dao.findByDataBetween(filtro.getDataInicial(), filtro.getDataFinal());
			
		}else{
			return dao.findAll();
		}
	}
	
	
}
