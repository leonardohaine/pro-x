package br.com.prox.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
public class Cidade {
	
	@Id
	@Column
	private String ibge;
	
	@Column
	private String siafi;
	
	@Column
	private String municipio;
	
	@Column
	private String uf;
	
	@Column
	private String timezone;
	
	@Column(name = "sigla_uf")
	private String siglaUf;
	
	@Column(name = "cod_municipio_goiania")
	private String codMunicipioGoiania;
	
	@Column
	private String imap;
	
	@Column(name = "descricao_municipio")
	private String descricaoMunicipio;
	
	@Column(name = "cod_municipio_bauhaus")
	private String codMunicipioBauhaus;

}
