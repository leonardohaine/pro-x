package br.com.prox.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.prox.model.Projeto;
import br.com.prox.repository.ProjetoDAO;
import lombok.Data;


@Service
public class ProjetoService {

	@Autowired
	private ProjetoDAO dao;
	
	@Transactional
	public void salvar(Projeto projeto){
		dao.save(projeto);
	}

	@Transactional
	public void excluir(Projeto projeto) {
		dao.delete(projeto);
	}
		
}
