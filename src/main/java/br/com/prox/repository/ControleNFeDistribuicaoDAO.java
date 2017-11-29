package br.com.prox.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.prox.model.ControleNFeDistribuicao;

@Repository
public interface ControleNFeDistribuicaoDAO extends JpaRepository<ControleNFeDistribuicao, Long> {
	
	
	@Query("select max(c.nsu) from ControleNFeDistribuicao c where empresa_id = :id and tipo_ambiente = :tipoAmbiente")
	Long getMaxNsuByEmpresaIdAndTipoAmbiente(@Param("id") Long empresaId, @Param("tipoAmbiente") String tipoAmbiente);

}
