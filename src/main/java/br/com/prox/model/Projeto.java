package br.com.prox.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Duration;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.Type;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.transaction.annotation.Transactional;

import lombok.Data;


@Entity
@Data
@Transactional
public class Projeto implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private Long id;
	
	@NotEmpty(message = "O nome do projeto é obrigatório")
	private String nome;
	
	private String descricao;
	
	private Duration estimativa;
	
	private BigDecimal taxa;
	
	@Type(type= "org.hibernate.type.NumericBooleanType")
    @Column(name = "ativo")
    private Boolean ativo;
	
	//@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.DETACH)
	//@Fetch(value = FetchMode.SUBSELECT)
	//private List<Contratante> contratante;
	
	@Fetch(value = FetchMode.SUBSELECT)
	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.DETACH)
	private List<Consultor> consultor;
	
	
}
