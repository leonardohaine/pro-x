package br.com.prox.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;

import br.com.prox.nfe.certificado.Certificado;
import lombok.Data;

@Entity
@Data
@Component
public class CertificadoDigital extends Certificado {

	  @Id
      @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_certificado")
      @SequenceGenerator(name = "seq_certificado", sequenceName = "seq_certificado",  allocationSize = 0)
	  private Long id;
	  
	  private String alias;
	  
	  private String password;
	  
	  private String email;
	  
	  private String keyStoreType;
	  
	  @Column(name = "key_store_blob", length = 1000000)
	  private byte[] keyStoreBlob;
	 
	  //@NotNull(message = "Data é obrigatória")
	  @DateTimeFormat(pattern = "dd/MM/yyyy")
	  @Temporal(TemporalType.DATE)
	  private Date data_expiracao;
	  
	  //@NotNull(message = "Data é obrigatória")
	  @DateTimeFormat(pattern = "dd/MM/yyyy")
	  @Temporal(TemporalType.DATE)
	  private Date data_emissao;
	  
	  private String serialNumber;

	  private String raiz;
	  
	  private String filial;
	  
	  private String digitos;
	  
	  private String todasFiliais;
	  
	  private String modulo;
	
}
