package br.com.prox.model;

import java.io.Serializable;

import javax.inject.Named;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import lombok.Data;

@Named
@Entity
@Data
public class Contratante implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_contratante")
    @SequenceGenerator(name = "seq_contratante", sequenceName = "seq_contratante",  allocationSize = 0)
	private Long id;
	
	@NotEmpty(message="O campo 'Nome' deve ser preenchido")
	private String nome;
	
	@NotEmpty(message="O campo 'Contato' deve ser preenchido")
	private String contato;
	
	@Email(message="Informe um email valido")
	private String email;
	
	@ManyToOne
	@JoinColumn(name = "projeto_id")
    private Projeto projeto;

}
