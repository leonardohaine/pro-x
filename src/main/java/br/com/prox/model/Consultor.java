package br.com.prox.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

import org.hibernate.annotations.Type;
import org.hibernate.validator.constraints.NotEmpty;

import lombok.Data;

@Entity
@Data
//@Audited
public class Consultor {

	@Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_consultor")
    @SequenceGenerator(name = "seq_consultor", sequenceName = "seq_consultor",  allocationSize = 0)
	private Long id;
	
	@NotEmpty(message = "O nome do consultor é obrigatório")
	private String nome;
	
	@Type(type= "org.hibernate.type.NumericBooleanType")
    @Column(name = "ativo")
    private Boolean ativo;
	
	@ManyToOne
	@JoinColumn(name = "projeto_id")
    private Projeto projeto;
	
}
