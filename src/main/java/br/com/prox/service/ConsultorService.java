package br.com.prox.service;

import javax.inject.Named;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.prox.model.Consultor;
import br.com.prox.repository.ConsultorDAO;
import lombok.Data;

@Service
public class ConsultorService {
	
	@Autowired
	private ConsultorDAO dao;
	
	public void salvar(Consultor consultor){
		dao.save(consultor);
	}

	public void excluir(Consultor consultor) {
		dao.delete(consultor);
	}

	
	
	
}
