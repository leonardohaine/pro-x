package br.com.prox.service;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.prox.model.Empresa;
import br.com.prox.repository.Cidades;
import br.com.prox.repository.Empresas;

@Service
public class EmpresaService implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Autowired
	private Empresas empresas;
	
	@Autowired
	private Cidades cidades;
	
	@Transactional
	public void salvar(Empresa empresa) {
		empresas.save(empresa);
	}
	
	@javax.transaction.Transactional
	public void excluir(Empresa empresa) {
		empresas.delete(empresa);
	}
	
	public List<String> getCidade(String uf) {
//		List<String> mun = ArrayList<String>;
//		for(String municipio : cidades.findBySiglaUf(uf)){
//			mun.add(municipio);
//		}
		
		return cidades.findBySiglaUf(uf);
	}
	
	public Empresa findByCpnj(String cnpj){
		return empresas.findByCnpj(cnpj);
	}
	
	public String getIbgeCidade(String uf, String descricaoMunicipio) {
		return cidades.findBySiglaUfAndDescricaoMunicipio(uf, descricaoMunicipio);
	}

}