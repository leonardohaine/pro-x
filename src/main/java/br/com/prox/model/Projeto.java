package br.com.prox.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.Type;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.transaction.annotation.Transactional;

import lombok.Data;


@Entity
@Data
@Transactional
//@Audited
public class Projeto implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_projeto")
    @SequenceGenerator(name = "seq_projeto", sequenceName = "seq_projeto",  allocationSize = 0)
	private Long id;
	
	@NotEmpty(message = "O nome do projeto é obrigatório")
	private String nome;
	
	private String descricao;
	
	@NotNull(message = "A estimativa é obrigatório")
	private Double estimativa;
	
	private BigDecimal taxa;
	
	@Column(length = 4000)
	private String wiki;
	
	@Type(type= "org.hibernate.type.NumericBooleanType")
    @Column(name = "ativo")
    private Boolean ativo;
	
	//@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.DETACH)
	//@Fetch(value = FetchMode.SUBSELECT)
	//private List<Contratante> contratante;
	
	@Fetch(value = FetchMode.SUBSELECT)
	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.DETACH)
	private List<Consultor> consultor;
	
	@Fetch(value = FetchMode.SUBSELECT)
	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.DETACH)
	private List<Arquivo> arquivos;
	
	
}
