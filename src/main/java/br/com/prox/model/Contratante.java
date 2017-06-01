package br.com.prox.model;

import java.io.Serializable;

import javax.inject.Named;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Data;

@Named
@Entity
@Data
public class Contratante implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private Long id;
	
	private String nome;
	
	private String contato;
	
	private String email;
	
	@ManyToOne
	@JoinColumn(name = "projeto_id")
    private Projeto projeto;

}
