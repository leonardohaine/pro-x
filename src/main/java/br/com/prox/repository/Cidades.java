package br.com.prox.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.prox.model.Cidade;

@Repository
public interface Cidades extends JpaRepository<Cidade, String> {
	
	@Query("select c.descricaoMunicipio from Cidade c where sigla_uf = :sigla_uf order by descricao_municipio")
	public List<String> findBySiglaUf(@Param("sigla_uf")String uf);
	
	@Query("select c.ibge from Cidade c where descricao_municipio = :descricao_municipio and sigla_uf = :sigla_uf order by descricao_municipio")
	public String findBySiglaUfAndDescricaoMunicipio(@Param("sigla_uf")String uf, @Param("descricao_municipio") String descricaoMunicipio);

}
