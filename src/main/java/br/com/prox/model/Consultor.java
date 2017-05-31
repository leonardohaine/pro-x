package br.com.prox.model;

import javax.inject.Named;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.Data;

@Named
@Entity
@Data
public class Consultor {

	@Id
	@GeneratedValue
	private Long id;
	
	private String nome;
	
}
