package br.com.prox.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.prox.model.Projeto;

@Repository
public interface ProjetoDAO extends JpaRepository<Projeto, Long> {

}
