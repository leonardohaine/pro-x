package br.com.prox.model;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

import lombok.Data;

@Entity
@Data
public class Processamento {
	
	@Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_processamento")
    @SequenceGenerator(name = "seq_processamento", sequenceName = "seq_processamento",  allocationSize = 0)
	private Long id;
	
	
	private String tipo;
	
	private LocalDateTime data;
	
	private String status;
	
	private Integer tentativas;
	
	private Integer idEmpresa;
	
	private String cnpjEmissor;
	
	private String ieEmissor;
	
	private String cnpjDestinatario;
	
	private String tipoAmbiente;
	
	private String serie;
	
	private String numeroNfe;
	
	private String versao;

}
