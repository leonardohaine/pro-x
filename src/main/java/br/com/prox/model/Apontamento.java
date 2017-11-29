package br.com.prox.model;

import java.io.Serializable;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;


@Entity
@Data
public class Apontamento implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_apontamento")
    @SequenceGenerator(name = "seq_apontamento", sequenceName = "seq_apontamento",  allocationSize = 0)
	private Long id;
	
	@NotEmpty(message = "Título é obrigatório")
	private String titulo;
	
	private String categoria;
	
	@NotNull(message = "Data é obrigatória")
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	@Temporal(TemporalType.DATE)
	private Date data;
	
//	private Calendar tempoGasto;

	@NotNull(message = "Tempo é obrigatório")
	@DateTimeFormat(pattern = "HH:mm")
	private LocalTime  tempoGasto;
	
	@NotNull(message = "Atividade é obrigatória")
	@Enumerated(EnumType.STRING)
	private AtividadeApontamento atividade;
	
	private String descricao;
	
	@NotEmpty(message = "Comentário é obrigatório")
	@Size(min = 20, max = 4000, message = "Comentário deve conter no mínimo 20 e no máximo 4000 caracteres")
	@Column(length = 4000)
	private String comentario;
	
	@NotNull(message = "Status é obrigatório")
	@Enumerated(EnumType.STRING)
	private StatusApontamento status;
	
	@NotNull(message = "Projeto é obrigatório")
	@OneToOne
	private Projeto projeto;
	
	//@OneToMany
	//private List<Consultor> consultor;
	
	
}