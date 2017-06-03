package br.com.prox.service;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

	
	
	
}
