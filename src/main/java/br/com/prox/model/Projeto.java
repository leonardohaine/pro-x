package br.com.prox.model;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;

import javax.inject.Named;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import lombok.Data;

@Named
@Entity
@Data
public class Projeto {

	@Id
	@GeneratedValue
	private Long id;
	
	private String titulo;
	
	private String categoria;
	
	private Date data;
	
	private Calendar tempoGasto;
	
//	private LocalDate tempoGasto;
	
	private String tipo;
	
	private String descricao;
	
	private String comentario;
	
	@OneToOne
	private Contratante contratante;
	
	@OneToMany
	private Consultor consultor;
	
	
}
