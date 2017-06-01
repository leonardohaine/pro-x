package br.com.prox.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.prox.model.Projeto;
import br.com.prox.repository.ProjetoDAO;
import lombok.Data;

@Transactional
@Service
public class ProjetoService {

	@Autowired
	private ProjetoDAO dao;
	
	public void salvar(Projeto projeto){
		dao.save(projeto);
	}

	public void excluir(Projeto projeto) {
		dao.delete(projeto);
	}
		
}
