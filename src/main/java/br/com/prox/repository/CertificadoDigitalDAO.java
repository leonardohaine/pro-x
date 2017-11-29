package br.com.prox.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.prox.model.CertificadoDigital;

@Repository
public interface CertificadoDigitalDAO extends JpaRepository<CertificadoDigital, Long> {

	CertificadoDigital findByRaiz(String raiz); 
	
}
