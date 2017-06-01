package br.com.prox.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.prox.model.Consultor;
import br.com.prox.repository.ConsultorDAO;

@Transactional
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

	public Consultor buscarId(Long id){
		try{
			return dao.findOne(id);
		}catch (Exception e) {
			System.out.println("Falha ao consultar consultor por id\n" + e);
			return null;
		}
	}
	
}
