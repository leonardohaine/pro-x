package br.com.prox.model;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

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
public class Apontamento implements Serializable {

	private static final long serialVersionUID = 1L;

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
	private Projeto projeto;
	
	@OneToMany
	private List<Consultor> consultor;
	
	
}
