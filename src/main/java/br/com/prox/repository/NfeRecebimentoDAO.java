package br.com.prox.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.prox.model.NfeRecebimento;

@Repository
public interface NfeRecebimentoDAO extends JpaRepository<NfeRecebimento, Long> {
	
	List<NfeRecebimento> findByCnpjEmpresa(String cnpj);

}
