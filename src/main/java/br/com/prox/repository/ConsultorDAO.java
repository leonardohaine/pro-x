package br.com.prox.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.prox.model.Consultor;

@Repository
public interface ConsultorDAO extends JpaRepository<Consultor, Long> {

}
