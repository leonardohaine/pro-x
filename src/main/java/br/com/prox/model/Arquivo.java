package br.com.prox.model;

import java.time.LocalTime;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;

import lombok.Data;

@Entity
@Data
public class Arquivo {
	@Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_file")
    @SequenceGenerator(name = "seq_file", sequenceName = "seq_file",  allocationSize = 0)
	private Long id;
	private String nome;
	private String descricao;
	private String tipo;
	private Long tamanho;
	private byte[] dados;
	
	@ManyToOne
	@JoinColumn(name = "projeto_id")
    private Projeto projeto;
}
