package br.com.prox.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

import org.springframework.stereotype.Component;

import lombok.Data;

@Entity
@Data
@Component
public class ControleNFeDistribuicao {

	@Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_controle_dist")
    @SequenceGenerator(name = "seq_controle_dist", sequenceName = "seq_controle_dist",  allocationSize = 0)
	private Long id;
	
	private Long empresaId;
	private Integer status;
	private String aplicacao;
	private Long nsu;
	private LocalDateTime data_ultima_consulta;
	
	@Column(name = "tipo_ambiente")
	private String tipoAmbiente;
	private String tipo_consulta;

}
