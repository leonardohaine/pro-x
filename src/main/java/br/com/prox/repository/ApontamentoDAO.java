package br.com.prox.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.prox.model.Apontamento;

@Repository
public interface ApontamentoDAO extends JpaRepository<Apontamento, Long> {

}
