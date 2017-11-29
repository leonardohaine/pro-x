package br.com.prox.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.prox.model.ControleNFeDistribuicao;
import br.com.prox.model.Empresa;
import br.com.prox.repository.ControleNFeDistribuicaoDAO;
import br.com.prox.repository.Empresas;
import br.com.prox.util.FacesMessages;


@Service
public class ControleNFeService {
	
	@Autowired
	private ControleNFeDistribuicaoDAO controleNFeDao;
	

	@Autowired
	private Empresas empresas;
	
	
	@Autowired
	private FacesMessages messages;
	
	@Transactional
	public void salvar(ControleNFeDistribuicao controleNFe){
		controleNFeDao.save(controleNFe);
	}

	@Transactional
	public void excluir(ControleNFeDistribuicao controleNFe) {
		controleNFeDao.delete(controleNFe);
	}
	
	public Long getMaxNsuByEmpresaIdAndTipoAmbiente(Empresa empresa, String ambiente){
		
		Long ultimoNsu = controleNFeDao.getMaxNsuByEmpresaIdAndTipoAmbiente(empresa.getId(), ambiente);
		
		return ultimoNsu;
	}
	
}
