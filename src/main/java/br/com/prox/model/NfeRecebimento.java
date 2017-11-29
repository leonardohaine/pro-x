package br.com.prox.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Type;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;

import lombok.Data;

@Data
@Entity
@Component
public class NfeRecebimento {

	@Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_nfe_recebimento")
    @SequenceGenerator(name = "seq_nfe_recebimento", sequenceName = "seq_nfe_recebimento",  allocationSize = 0)
	private Long id;

	@NotNull
	@Column(name = "cnpj_empresa", length = 14)
	private String cnpjEmpresa;

	@NotNull
	@Column(length = 14)
	private String ie_empresa;

	@NotNull
	@Column(length = 1)
	private String tipo_ambiente_sistema;

	@NotNull
	@Column(length = 1)
	private String tipo_emissao;

	@NotNull
	private Long numero_nfe;

	@NotNull
	@Column(length = 3)
	private String serie;

	@NotNull
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	//@Temporal(TemporalType.DATE)
	private LocalDateTime data_emissao;

	@NotNull
	@Column(length = 4)
	private String versao;

	@NotNull
	@Column(length = 44)
	private String chave;

	public Integer entrada_saida;

	private BigDecimal valor;

	@NotNull
	@Column(length = 14)
	private String cnpj_remetente;

	@NotNull
	@Column(length = 120)
	private String razao_social_remetente;

	@NotNull
	@Column(length = 2)
	private String uf_remetente;

	@DateTimeFormat(pattern = "dd/MM/yyyy")
	//@Temporal(TemporalType.DATE)
	private LocalDateTime data_autorizacao;

	@DateTimeFormat(pattern = "dd/MM/yyyy")
	//@Temporal(TemporalType.DATE)
	private LocalDateTime data_cancelmaento;

	@Type(type = "org.hibernate.type.NumericBooleanType")
	private Boolean documento_auxiliar_recebido;

	@Column(length = 35)
	private String digest_value_emissao;

	@Column(length = 25)
	private String numero_protocolo_emissao;

	@Column(length = 35)
	private String digest_value_canc;

	@Column(length = 25)
	private String numero_protocolo_canc;

	private Boolean assinatura_invalida_emissao;

	@Type(type = "org.hibernate.type.NumericBooleanType")
	private Boolean assinatura_invalida_canc;

	@Type(type = "org.hibernate.type.NumericBooleanType")
	private Boolean numero_protocolo_invalido;

	@Type(type = "org.hibernate.type.NumericBooleanType")
	private Boolean digest_value_invalido;

	@Type(type = "org.hibernate.type.NumericBooleanType")
	private Boolean cancelamento_sistema;

	@Type(type = "org.hibernate.type.NumericBooleanType")
	private Boolean arquivo_emissao_proc;

	@Type(type = "org.hibernate.type.NumericBooleanType")
	private Boolean arquivo_canc_proc;

	@Column(length = 255)
	private String referencia;

	@NotNull
	private Integer status;

	@Column(length = 2000)
	private String descricao_status;

	@NotNull
	@Column(length = 5)
	private String tipo_documento;

	@NotNull
	@Column(length = 30)
	private String tipo_xml;

	private Long nfe_doc_xml_emissao_id;
	private Long nfe_doc_xml_cancelamento_id;
	private Long nfe_doc_xml_cons_emissao_id;
	private Long nfe_doc_xml_cons_emis_ret_id;
	private Long nfe_doc_xml_cons_canc_id;
	private Long nfe_doc_xml_cons_canc_ret_id;
	private Integer codigo_evento;

	@Type(type = "org.hibernate.type.NumericBooleanType")
	private Boolean reconsulta;

	@Type(type = "org.hibernate.type.NumericBooleanType")
	private Boolean manifesto_consultado;

	private Integer status_consulta_destinada;

	@Column(length = 5)
	private String uf_destinatario;

	@Type(type = "org.hibernate.type.NumericBooleanType")
	private Boolean migracao;

	@Type(type = "org.hibernate.type.NumericBooleanType")
	private Boolean impresso;

	private Long nsu;

	@NotNull
	@Type(type = "org.hibernate.type.NumericBooleanType")
	private Boolean download_xml;
	
	//@NotNull
	@Type(type = "org.hibernate.type.NumericBooleanType")
	private Boolean email_enviado;

	@DateTimeFormat(pattern = "dd/MM/yyyy")
	//@Temporal(TemporalType.DATE)
	private LocalDateTime data_importacao;

	private Integer status_impressao;

	@Column(length = 3)
	private String modelo;

	@Column(length = 2)
	private String modal;

	@Column(length = 1)
	private String tipo_servico;

}
