package br.com.prox.service;

import javax.inject.Named;

import org.springframework.stereotype.Service;

import br.com.prox.model.Consultor;
import br.com.prox.repository.ConsultorDAO;
import lombok.Data;

@Service
@Data
public class ConsultorService {
	
	
	
	private ConsultorDAO dao;
	
//	private Consultor consultor;
	
	public void salvar(Consultor consultor){
		
		dao.save(consultor);
		
		
	}

	
	
	
}
