package br.com.prox.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;

import lombok.Data;

@Entity
@Data
@Component
public class DocumentoXml {

	@Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_doc_xml")
    @SequenceGenerator(name = "seq_doc_xml", sequenceName = "seq_doc_xml",  allocationSize = 0)
	private Long id;
	
	@Column(name = "tipo")
	private String tipo;
	
	@Column(name = "documento")
	private String documento;
	
	//@Lob
	@Column(name = "xml")
	private byte[] xml;
	
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private LocalDateTime data_gravacao;
	
}
