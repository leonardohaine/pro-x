package br.com.prox.nfe.danfe;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Date;

@JsonIgnoreProperties(ignoreUnknown=true)
public class ParamsImpressaoDoctoFiscal
{
  public Long id;
  public String conteudoXml;
  public String versaoXml;
  public Date dataEmissao;
  public Integer nroCopias;
  public String impressoraAlvo;
  public Integer formatoImpressao;
  public boolean isNotaRecebimento;
  public boolean isContingencia;
  public String dataAutorizacao;
  public String nroProtocolo;
  public String emailEmpresa;
  public String emailEmitente;
  public Date dataRecebimentoDpec;
  public String nroRecebimentoDpec;
  public Integer casasDecimaisValorUnit;
  public String diretorioModeloDoctoFiscalCustomizado;
  public Integer statusDocto;
  public String xmlEnvioCC;
  public String xmlRetornoCC;
  public String xmlCancelamento;
  public Integer casasDecimaisQuantidade;
  public String valorAproximadoTributos;
  public String ordemEmbarque;
  public String nroItens;
  public String usuarioEmitente;
  public String mensagemContingencia;
  public String cnpjEmissor;
  public String inscricaoEmissor;
  public String urlConsultaNota;
  public String urlConsultaDanfe;
  public Integer viaNotaEmissao;
  public Boolean contingenciaEpec;
  public boolean falsaImpressao;
  public long sequenciaImpressao;
  public Integer valorTributosDanfe;
  public boolean exibirDadosConsumidorDanfe;
  public boolean exibirChaveReferenciadaDanfe;
  public boolean exibirValoresIcmsInterestadual;
  public boolean exibirValorTributavelProduto;
  public Integer enviaPrinter;
  public String nomenclaturaArquivos;
  
  @JsonCreator
  public ParamsImpressaoDoctoFiscal(@JsonProperty("id") Long id, @JsonProperty("conteudoXml") String conteudoXml, @JsonProperty("versaoXml") String versaoXml, @JsonProperty("dataEmissao") Date dataEmissao, @JsonProperty("nroCopias") Integer nroCopias, @JsonProperty("impressoraAlvo") String impressoraAlvo, @JsonProperty("formatoImpressao") Integer formatoImpressao, @JsonProperty("notaRecebimento") boolean isNotaRecebimento, @JsonProperty("isContingencia") boolean isContingencia, @JsonProperty("dataAutorizacao") String dataAutorizacao, @JsonProperty("nroProtocolo") String nroProtocolo, @JsonProperty("emailEmpresa") String emailEmpresa, @JsonProperty("emailEmitente") String emailEmitente, @JsonProperty("dataRecebimentoDpec") Date dataRecebimentoDpec, @JsonProperty("nroRecebimentoDpec") String nroRecebimentoDpec, @JsonProperty("casasDecimaisValorUnit") Integer casasDecimaisValorUnit, @JsonProperty("diretorioModeloDoctoFiscalCustomizado") String diretorioModeloDoctoFiscalCustomizado, @JsonProperty("statusDocto") Integer statusDocto, @JsonProperty("xmlEnvioCC") String xmlEnvioCC, @JsonProperty("xmlRetornoCC") String xmlRetornoCC, @JsonProperty("xmlCancelamento") String xmlCancelamento, @JsonProperty("casasDecimaisQuantidade") Integer casasDecimaisQuantidade, @JsonProperty("valorAproximadoTributos") String valorAproximadoTributos, @JsonProperty("mensagemContingencia") String mensagemContingencia, @JsonProperty("cnpjEmissor") String cnpjEmissor, @JsonProperty("inscricaoEmissor") String inscricaoEmissor, @JsonProperty("urlConsultaNota") String urlConsultaNota, @JsonProperty("urlConsultaDanfe") String urlConsultaDanfe, @JsonProperty("viaNotaEmissao") Integer viaNotaEmissao, @JsonProperty("contingenciaEpec") Boolean contingenciaEpec, @JsonProperty("falsaImpressao") boolean falsaImpressao, @JsonProperty("sequenciaImpressao") long sequenciaImpressao, @JsonProperty("valorTributosDanfe") Integer valorTributosDanfe, @JsonProperty("exibirDadosConsumidorDanfe") boolean exibirDadosConsumidorDanfe, @JsonProperty("exibirChaveReferenciadaDanfe") boolean exibirChaveReferenciadaDanfe, @JsonProperty("exibirValoresIcmsInterestadual") boolean exibirValoresIcmsInterestadual, @JsonProperty("exibirValorTributavelProduto") boolean exibirValorTributavelProduto, @JsonProperty("enviaPrinter") Integer enviaPrinter, @JsonProperty("nomenclaturaArquivos") String nomenclaturaArquivos)
  {
    this.id = id;
    this.conteudoXml = conteudoXml;
    this.versaoXml = versaoXml;
    this.dataEmissao = dataEmissao;
    this.nroCopias = nroCopias;
    this.impressoraAlvo = impressoraAlvo;
    this.formatoImpressao = formatoImpressao;
    
    this.isNotaRecebimento = isNotaRecebimento;
    this.isContingencia = isContingencia;
    this.dataAutorizacao = dataAutorizacao;
    this.nroProtocolo = nroProtocolo;
    this.emailEmpresa = emailEmpresa;
    this.emailEmitente = emailEmitente;
    this.dataRecebimentoDpec = dataRecebimentoDpec;
    this.nroRecebimentoDpec = nroRecebimentoDpec;
    this.casasDecimaisValorUnit = casasDecimaisValorUnit;
    this.diretorioModeloDoctoFiscalCustomizado = diretorioModeloDoctoFiscalCustomizado;
    this.statusDocto = statusDocto;
    this.xmlEnvioCC = xmlEnvioCC;
    this.xmlRetornoCC = xmlRetornoCC;
    this.xmlCancelamento = xmlCancelamento;
    this.casasDecimaisQuantidade = casasDecimaisQuantidade;
    this.valorAproximadoTributos = valorAproximadoTributos;
    this.mensagemContingencia = mensagemContingencia;
    this.cnpjEmissor = cnpjEmissor;
    this.inscricaoEmissor = inscricaoEmissor;
    this.urlConsultaNota = urlConsultaNota;
    this.urlConsultaDanfe = urlConsultaDanfe;
    this.viaNotaEmissao = viaNotaEmissao;
    this.contingenciaEpec = contingenciaEpec;
    this.falsaImpressao = falsaImpressao;
    this.sequenciaImpressao = sequenciaImpressao;
    this.valorTributosDanfe = valorTributosDanfe;
    this.exibirDadosConsumidorDanfe = exibirDadosConsumidorDanfe;
    this.exibirChaveReferenciadaDanfe = exibirChaveReferenciadaDanfe;
    this.exibirValoresIcmsInterestadual = exibirValoresIcmsInterestadual;
    this.exibirValorTributavelProduto = exibirValorTributavelProduto;
    this.enviaPrinter = enviaPrinter;
    this.nomenclaturaArquivos = nomenclaturaArquivos;
  }
  
  public ParamsImpressaoDoctoFiscal(Long id, String conteudoXml, String versaoXml, Date dataEmissao, Integer nroCopias, String impressoraAlvo, Integer formatoImpressao, boolean isNotaRecebimento, boolean isContingencia, String dataAutorizacao, String nroProtocolo, String emailEmpresa, String emailEmitente, Date dataRecebimentoDpec, String nroRecebimentoDpec, Integer casasDecimaisValorUnit, String diretorioModeloDoctoFiscalCustomizado, Integer statusDocto, String xmlEnvioCC, String xmlRetornoCC, String xmlCancelamento, Integer casasDecimaisQuantidade, String valorAproximadoTributos, String mensagemContingencia, String cnpjEmissor, String inscricaoEmissor, String urlConsultaNota, String urlConsultaDanfe, Integer viaNotaEmissao, Boolean contingenciaEpec, boolean falsaImpressao, boolean exibirChaveReferenciadaDanfe, boolean exibirValoresIcmsInterestadual, boolean exibirValorTributavelProduto)
  {
    this(id, conteudoXml, versaoXml, dataEmissao, nroCopias, impressoraAlvo, formatoImpressao, isNotaRecebimento, isContingencia, dataAutorizacao, nroProtocolo, emailEmpresa, emailEmitente, dataRecebimentoDpec, nroRecebimentoDpec, casasDecimaisValorUnit, diretorioModeloDoctoFiscalCustomizado, statusDocto, xmlEnvioCC, xmlRetornoCC, xmlCancelamento, casasDecimaisQuantidade, valorAproximadoTributos, mensagemContingencia, cnpjEmissor, inscricaoEmissor, urlConsultaNota, urlConsultaDanfe, viaNotaEmissao, contingenciaEpec, falsaImpressao, 0L, null, true, exibirChaveReferenciadaDanfe, exibirValoresIcmsInterestadual, exibirValorTributavelProduto, Integer.valueOf(0), null);
  }
  
  public ParamsImpressaoDoctoFiscal(Long id, String conteudoXml, String versaoXml, Date dataEmissao, Integer nroCopias, String impressoraAlvo, Integer formatoImpressao, boolean isNotaRecebimento, boolean isContingencia, String dataAutorizacao, String nroProtocolo, String emailEmpresa, String emailEmitente, Date dataRecebimentoDpec, String nroRecebimentoDpec, Integer casasDecimaisValorUnit, String diretorioModeloDoctoFiscalCustomizado, String cnpjEmissor, String inscricaoEmissor, Boolean contingenciaEpec)
  {
    this(id, conteudoXml, versaoXml, dataEmissao, nroCopias, impressoraAlvo, formatoImpressao, isNotaRecebimento, isContingencia, dataAutorizacao, nroProtocolo, emailEmpresa, emailEmitente, dataRecebimentoDpec, nroRecebimentoDpec, casasDecimaisValorUnit, diretorioModeloDoctoFiscalCustomizado, Integer.valueOf(0), null, null, null, null, null, null, cnpjEmissor, inscricaoEmissor, null, null, null, contingenciaEpec, false, 0L, null, true, true, true, true, Integer.valueOf(0), null);
  }
  
  public void filtros(String impressora, String usuarioEmitente, String ordemEmbarque, String nroItens)
  {
    this.impressoraAlvo = impressora;
    this.usuarioEmitente = usuarioEmitente;
    this.ordemEmbarque = ordemEmbarque;
    this.nroItens = nroItens;
  }
  
  public static Long id(Long id)
  {
    return id;
  }
  
  public static String conteudoXml(String conteudoXml)
  {
    return conteudoXml;
  }
  
  public static Date dataEmissao(Date dataEmissao)
  {
    return dataEmissao;
  }
  
  public static String versaoXml(String versaoXml)
  {
    return versaoXml;
  }
  
  public static Integer nroCopias(Integer nroCopias)
  {
    return nroCopias;
  }
  
  public static String impressoraAlvo(String impressoraAlvo)
  {
    return impressoraAlvo;
  }
  
  public static Integer formatoImpressao(Integer formatoImpressao)
  {
    return formatoImpressao;
  }
  
  public static boolean isNotaRecebimento(boolean isNotaRecebimento)
  {
    return isNotaRecebimento;
  }
  
  public static boolean isContingencia(boolean isContingencia)
  {
    return isContingencia;
  }
  
  public static String dataAutorizacao(String dataAutorizacao)
  {
    return dataAutorizacao;
  }
  
  public static String nroProtocolo(String nroProtocolo)
  {
    return nroProtocolo;
  }
  
  public static String emailEmpresa(String emailEmpresa)
  {
    return emailEmpresa;
  }
  
  public static String emailEmitente(String emailEmitente)
  {
    return emailEmitente;
  }
  
  public static Date dataRecebimentoDpec(Date dataRecebimentoDpec)
  {
    return dataRecebimentoDpec;
  }
  
  public static String nroRecebimentoDpec(String nroRecebimentoDpec)
  {
    return nroRecebimentoDpec;
  }
  
  public static Integer casasDecimaisValorUnit(Integer casasDecimaisValorUnit)
  {
    return casasDecimaisValorUnit;
  }
  
  public static Integer casasDecimaisQuantidade(Integer casasDecimaisQuantidade)
  {
    return casasDecimaisQuantidade;
  }
  
  public static String diretorioModeloDoctoFiscalCustomizado(String diretorioModeloDoctoFiscalCustomizado)
  {
    return diretorioModeloDoctoFiscalCustomizado;
  }
  
  public static Integer statusDocto(Integer statusDocto)
  {
    return statusDocto;
  }
  
  public static String xmlEnvioCC(String xmlEnvioCC)
  {
    return xmlEnvioCC;
  }
  
  public static String xmlRetornoCC(String xmlRetornoCC)
  {
    return xmlRetornoCC;
  }
  
  public static String xmlCancelamento(String xmlCancelamento)
  {
    return xmlCancelamento;
  }
  
  public static String valorAproximadoTributos(String valorAproximadoTributos)
  {
    return valorAproximadoTributos;
  }
  
  public static String ordemEmbarque(String ordemEmbarque)
  {
    return ordemEmbarque;
  }
  
  public static String usuarioEmitente(String usuarioEmitente)
  {
    return usuarioEmitente;
  }
  
  public static String nroItens(String nroItens)
  {
    return nroItens;
  }
  
  public static String mensagemContingencia(String mensagemContingencia)
  {
    return mensagemContingencia;
  }
  
  public static String cnpjEmissor(String cnpjEmissor)
  {
    return cnpjEmissor;
  }
  
  public static String inscricaoEmissor(String inscricaoEmissor)
  {
    return inscricaoEmissor;
  }
  
  public static String urlConsultaNota(String urlConsultaNota)
  {
    return urlConsultaNota;
  }
  
  public static String urlConsultaDanfe(String urlConsultaDanfe)
  {
    return urlConsultaDanfe;
  }
  
  public static Integer viaNotaEmissao(Integer viaNotaEmissao)
  {
    return viaNotaEmissao;
  }
  
  public static Boolean contingenciaEpec(Boolean contingenciaEpec)
  {
    return contingenciaEpec;
  }
  
  public static long sequenciaImpressao(Long sequenciaImpressao)
  {
    return sequenciaImpressao.longValue();
  }
  
  public static boolean falsaImpressao(boolean falsaImpressao)
  {
    return falsaImpressao;
  }
  
  public static Integer valorTributosDanfe(Integer valorTributosDanfe)
  {
    return valorTributosDanfe;
  }
  
  public static boolean exibirDadosConsumidorDanfe(boolean exibirDadosConsumidorDanfe)
  {
    return exibirDadosConsumidorDanfe;
  }
  
  public static boolean exibirChaveReferenciadaDanfe(boolean exibirChaveReferenciadaDanfe)
  {
    return exibirChaveReferenciadaDanfe;
  }
  
  public static boolean exibirValoresIcmsInterestadual(boolean exibirValoresIcmsInterestadual)
  {
    return exibirValoresIcmsInterestadual;
  }
  
  public static boolean exibirValorTributavelProduto(boolean exibirValorTributavelProduto)
  {
    return exibirValorTributavelProduto;
  }
}
