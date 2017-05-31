package br.com.prox.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.prox.model.Contratante;

@Repository
public interface ContratanteDAO extends JpaRepository<Contratante, Long> {
	
}
