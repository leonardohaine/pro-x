package br.com.prox.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.prox.model.Apontamento;
import br.com.prox.model.AtividadeApontamento;
import br.com.prox.model.Projeto;

@Repository
public interface ApontamentoDAO extends JpaRepository<Apontamento, Long> {
	
	public List<Apontamento> findByProjeto(Projeto id);
	public List<Apontamento> findByAtividade(AtividadeApontamento atividade);
	public List<Apontamento> findByProjetoAndAtividade(Projeto id, AtividadeApontamento atividade);

}
