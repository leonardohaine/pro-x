package br.com.prox.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.prox.model.Arquivo;

public interface ArquivoDAO extends JpaRepository<Arquivo, Long> {

}
		