package br.com.prox.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.prox.model.Empresa;


@Repository
public interface Empresas extends JpaRepository<Empresa, Long> {

	List<Empresa> findByConsultaDistribuicaoDFe(Boolean ativo);
	
	Empresa findByCnpj(String cnpj);
	
}
