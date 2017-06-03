package br.com.prox.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.prox.model.Consultor;
import br.com.prox.repository.ConsultorDAO;


@Service
public class ConsultorService {
	
	@Autowired
	private ConsultorDAO dao;
	
	@Transactional
	public void salvar(Consultor consultor){
		dao.save(consultor);
	}

	@Transactional
	public void excluir(Consultor consultor) {
		dao.delete(consultor);
	}
	
}
