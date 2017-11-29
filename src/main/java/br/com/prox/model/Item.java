package br.com.prox.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

import lombok.Data;

@Entity
@Data
public class Item {
	
	@Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_item")
    @SequenceGenerator(name = "seq_item", sequenceName = "seq_item",  allocationSize = 0)
	private Long id;
	
	@Column(name = "descricao")
	private String descricao;
	
	@Column(name = "quantidade")
	private Integer quantidade;
	
}
