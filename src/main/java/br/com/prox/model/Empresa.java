package br.com.prox.model;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;

import org.hibernate.annotations.Type;
import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.br.CNPJ;

import lombok.Data;

@Entity
@Table(name = "empresa")
@Data
public class Empresa implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private Long id;

	//@NotEmpty(message = "Nome fantasia não pode ser vazio")
	@Column(name = "nome_fantasia", nullable = false, length = 80)
	private String nomeFantasia;

	@NotEmpty
	@Column(name = "razao_social", nullable = false, length = 120)
	private String razaoSocial;

	@NotEmpty
	@CNPJ
	@Column(nullable = false, length = 18)
	private String cnpj;
	
	@Column(name = "cpf")
    private String cpf;
    
    @Column(name = "inscricao_estadual")
    private String inscricaoEstadual;
    
    @Column(name = "inscricao_estadual_st")
    private Long inscricaoEstadualST;
    
    @Column(name = "regime_tributario")
    private Long regimeTributario;
    
    @Column(name = "cnae_fiscal")
    private Long cnaeFiscal;
    
    @Column(name = "inscricao_municipal")
    private String inscricaoMunicipal;
    
    @Column(name = "suframa")
    private BigInteger suframa;
    
    @Column(name = "isento")
    private Boolean isento;
    
    @Column(name = "email")
    private String email;
    
    @Column(name = "cep")
    private String cep;
    
    @Column(name = "logradouro")
    private String logradouro;
    
    @Column(name = "numero")
    private String numero;
    
    @Column(name = "complemento")
    private String complemento;
    
    @Column(name = "bairro")
    private String bairro;
    
    @Column(name = "codigo_municipio")
    private Integer codigoMunicipio;
    
    @Column(name = "municipio")
    private String municipio;
    
    @Column(name = "codigo_pais")
    private Integer codigoPais;
    
    @Column(name = "pais")
    private String pais;
    
    @Column(name = "uf")
    private String uf;
    
    @Column(name = "telefone")
    private String telefone;
    
    @Type(type= "org.hibernate.type.NumericBooleanType")
    @Column(name = "ativo")
    private Boolean ativo;
    
    @Type(type= "org.hibernate.type.NumericBooleanType")
    @Column(name = "consultaDistribuicaoDFe")
    private Boolean consultaDistribuicaoDFe;
    
    @Column(name = "ambiente")
    private String ambiente;
    
    @Column(name = "versaoNfe")
    private String versaoNfe;

	//@NotNull
	@Past
	@Temporal(TemporalType.DATE)
	@Column(name = "data_fundacao")
	private Date dataFundacao;

	@NotNull
	@Enumerated(EnumType.STRING)
	private TipoEmpresa tipo;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNomeFantasia() {
		return nomeFantasia;
	}

	public void setNomeFantasia(String nomeFantasia) {
		this.nomeFantasia = nomeFantasia;
	}

	public String getRazaoSocial() {
		return razaoSocial;
	}

	public void setRazaoSocial(String razaoSocial) {
		this.razaoSocial = razaoSocial;
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public Date getDataFundacao() {
		return dataFundacao;
	}

	public void setDataFundacao(Date dataFundacao) {
		this.dataFundacao = dataFundacao;
	}

	public TipoEmpresa getTipo() {
		return tipo;
	}

	public void setTipo(TipoEmpresa tipo) {
		this.tipo = tipo;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Empresa other = (Empresa) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
}