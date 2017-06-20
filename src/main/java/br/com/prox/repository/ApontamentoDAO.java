package br.com.prox.repository;

import java.time.LocalTime;
import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.prox.model.Apontamento;
import br.com.prox.model.AtividadeApontamento;
import br.com.prox.model.Projeto;

@Repository
public interface ApontamentoDAO extends JpaRepository<Apontamento, Long> {
	
	@Query("select a from Apontamento a where projeto = :id")
	public List<Apontamento> totalHoras(@Param("id") Projeto id);
	
	//Busca por Projeto
	public List<Apontamento> findByProjeto(Projeto id);
	
	//Busca por projeto ou intervalo de datas
	public List<Apontamento> findByProjetoAndDataBetween(Projeto id, Date dataInicial, Date dataFinal);
	
	//Busca por atividade
	public List<Apontamento> findByAtividade(AtividadeApontamento atividade);
	
	//Busca por atividade e intervalo de datas
	public List<Apontamento> findByAtividadeAndDataBetween(AtividadeApontamento atividade, Date dataInicial, Date dataFinal);
	
	//Busca por projeto e atividade
	public List<Apontamento> findByProjetoAndAtividade(Projeto id, AtividadeApontamento atividade);
	
	//Busca por intervalo de datas
	public List<Apontamento> findByDataBetween(Date dataInicial, Date dataFinal);

}
