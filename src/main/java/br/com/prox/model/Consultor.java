package br.com.prox.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.Type;
import org.hibernate.envers.Audited;
import org.hibernate.validator.constraints.NotEmpty;

import lombok.Data;

@Entity
@Data
@Audited
public class Consultor {

	@Id
	@GeneratedValue
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
