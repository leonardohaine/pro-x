package br.com.prox.model;

import java.io.Serializable;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Type;

import lombok.Data;


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
	
//	private Calendar tempoGasto;

	private LocalTime  tempoGasto;
	
	private String tipo;
	
	private String descricao;
	
	private String comentario;
	
	@OneToOne
	private Projeto projeto;
	
	@OneToMany
	private List<Consultor> consultor;
	
	
}
