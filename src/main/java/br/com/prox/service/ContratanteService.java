package br.com.prox.service;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.prox.model.Consultor;
import br.com.prox.model.Contratante;
import br.com.prox.repository.ContratanteDAO;
import lombok.Data;

@Service
@Data
public class ContratanteService {

	@Autowired
	private ContratanteDAO dao;
	
	
	
	public void salvar(Contratante contratante){
		dao.save(contratante);
	}

	public void excluir(Contratante contratante) {
		dao.delete(contratante);
	}
	

	


	
	
}
