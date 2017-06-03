package br.com.prox.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.Type;
import org.springframework.transaction.annotation.Transactional;

import lombok.Data;

//@Named
@Entity
@Data
@Transactional
public class Consultor {

	@Id
	@GeneratedValue
	private Long id;
	
	private String nome;
	
	@Type(type= "org.hibernate.type.NumericBooleanType")
    @Column(name = "ativo")
    private Boolean ativo;
	
	@ManyToOne
	@JoinColumn(name = "projeto_id")
    private Projeto projeto;
	
}
