package br.com.prox.model;

import javax.inject.Named;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.annotations.Type;

import lombok.Data;

//@Named
@Entity
@Data
public class Consultor {

	@Id
	@GeneratedValue
	private Long id;
	
	private String nome;
	
	@Type(type= "org.hibernate.type.NumericBooleanType")
    @Column(name = "ativo")
    private Boolean ativo;
	
}
