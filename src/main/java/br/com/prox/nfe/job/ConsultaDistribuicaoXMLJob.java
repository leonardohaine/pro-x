package br.com.prox.nfe.job;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.xml.bind.JAXBException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import br.com.prox.model.ControleNFeDistribuicao;
import br.com.prox.model.DocumentoXml;
import br.com.prox.model.Empresa;
import br.com.prox.model.NfeRecebimento;
import br.com.prox.nfe.ConfiguracoesIniciaisNfe;
import br.com.prox.nfe.DownloadNFe;
import br.com.prox.nfe.Evento;
import br.com.prox.nfe.exception.NfeException;
import br.com.prox.nfe.util.XmlUtil;
import br.com.prox.repository.DocumentoXmlDAO;
import br.com.prox.repository.Empresas;
import br.com.prox.service.ControleNFeService;
import br.com.prox.service.DocumentoXmlService;
import br.com.prox.service.NfeRecebimentoService;
import br.inf.portalfiscal.nfe.envConfRecebto_v100.TEnvEvento;
import br.inf.portalfiscal.nfe.envConfRecebto_v100.TEvento;
import br.inf.portalfiscal.nfe.envEventoCancNFe_v1.TProcEvento;
import br.inf.portalfiscal.nfe.resEvento_v1.ResEvento;
import br.inf.portalfiscal.nfe.resNFe_v1.ResNFe;
import br.inf.portalfiscal.nfe.retDistDFeInt_v1.RetDistDFeInt;
import br.inf.portalfiscal.nfe.retDistDFeInt_v1.RetDistDFeInt.LoteDistDFeInt.DocZip;

@Component
public class ConsultaDistribuicaoXMLJob {

//	Se o NSU informado (tag:ultNSU) for igual ao maior NSU do Ambiente
//	Nacional (tag:maxNSU), então não existem mais documentos a serem pesquisados no momento.
	
	private final static String CIENCIA_DA_OPERACO= "210210";
	private final static String CONFIRMACAO_OPERACAO= "210200";
	private final static String DESCONHECIMENTO_OPERACAO= "210220";
	private final static String OPERACAO_NAO_REALIZADA= "210240";
	
	
	@Autowired
	private Empresas empresas;
	
	@Autowired
	private DocumentoXmlDAO documentoXmlDAO;
	
	@Autowired
	private DocumentoXmlService documentoXmlService;
	
	@Autowired
	private DocumentoXml documentoXml;
	
	@Autowired
    private ControleNFeService controleService;
	
	@Autowired
    private ControleNFeDistribuicao controleNFe;
	
	@Autowired
	private NfeRecebimento nfeRecebimento;
	
	@Autowired
	private NfeRecebimentoService nfeRecebimentoService;
	
	@Autowired
	private DownloadNFe download;
	
	private static String xmlEnvioManifestacao;
	private static String xmlRetornoManifestacao;
	
	//@Scheduled(fixedRate = 360000)
	public void consulta(){
		System.out.println("Buscando empresas habilitadas para consulta WS Distribuição XML");
		System.out.println("Data e hora: " + LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")));
		for(Empresa empresa : empresas.findByConsultaDistribuicaoDFe(Boolean.TRUE)){
			Long ultimoNsu = buscaUltNsu(empresa);
			consultaDocumentos(empresa, ultimoNsu);
		}
	}
	
	public void consultaDocumentos(Empresa empresa, Long UltNsu){
		try{
			//for(Empresa empresa : empresas.findByConsultaDistribuicaoDFe(Boolean.TRUE)){
				System.out.println("CNPJ: " + empresa.getCnpj().concat(" - ").concat(empresa.getRazaoSocial()));
				
				RetDistDFeInt retorno = download.downloadNFeNSU(empresa, UltNsu);
				
				System.out.println("Status:" + retorno.getCStat());
				System.out.println("Motivo:" + retorno.getXMotivo());
				System.out.println("NSU:" + retorno.getUltNSU());
				System.out.println("Max NSU:" + retorno.getMaxNSU());
				
				List<DocZip> listaDoc = null;
				if (retorno.getLoteDistDFeInt() != null) {
					listaDoc = retorno.getLoteDistDFeInt().getDocZip();
					System.out.println("Encontrado "+listaDoc.size() + " Documentos.");
				}
				
				if(retorno.getCStat().equals("137")){ // Nenhum documento localizado
					System.out.println(retorno.getCStat().concat(" - ".concat(retorno.getXMotivo())));
				}else if(retorno.getCStat().equals("138")){ // Documento localizado
					for (DocZip docZip : listaDoc) {
						System.out.println("Schema: "+docZip.getSchema());
						System.out.println("NSU:" + docZip.getNSU());
						System.out.println("XML: "+XmlUtil.gZipToXml(docZip.getValue()));
						
						
						
						switch(docZip.getSchema()){
						
						case "procNFe_v3.10.xsd" :
							documentoXml = new DocumentoXml();
							nfeRecebimento = new NfeRecebimento();
							
							documentoXml = documentoXmlService.salvar(documentoXml, docZip);
							nfeRecebimentoService.salvar(nfeRecebimento, documentoXml, docZip);
							
							controleNFe.setEmpresaId(empresa.getId());
							controleNFe.setNsu(Long.valueOf(retorno.getMaxNSU()));
							controleNFe.setStatus(Integer.valueOf(retorno.getCStat()));
							controleNFe.setTipoAmbiente(empresa.getAmbiente());
							controleNFe.setData_ultima_consulta(LocalDateTime.now());
							controleNFe.setAplicacao("nfe");
							controleService.salvar(controleNFe);
							
							break;
						case "procEventoNFe_v1.00.xsd" :
							
							documentoXml = new DocumentoXml();
							nfeRecebimento = new NfeRecebimento();
							
							documentoXml = documentoXmlService.salvar(documentoXml, docZip);
							nfeRecebimentoService.salvarCancelamento(nfeRecebimento, documentoXml, docZip);
							
							break;
						case "resNFe_v1.01.xsd" :
							ResNFe resNFe = XmlUtil.xmlToObject(XmlUtil.gZipToXml(docZip.getValue()), ResNFe.class);
							
							System.out.println("=========================================================================");
							System.out.println("Chave de acesso NFe: " + resNFe.getChNFe());
							System.out.println("CNPJ: " + empresa.getCnpj());
							System.out.println("NSU: " + docZip.getNSU());
							System.out.println("Iniciando manifestação do destinatário - Ciencia da operação");
							System.out.println("=========================================================================");
							
							if(resNFe.getCSitNFe().equals("1")){
								cienciaOperacao(resNFe, empresa.getCnpj().replaceAll("[^0-9]", ""));
							
								documentoXml = new DocumentoXml();
								nfeRecebimento = new NfeRecebimento();
								
								if (xmlRetornoManifestacao != null) {
									documentoXml.setTipo("XML de envio manifestação do destinatário");
									documentoXml.setXml(xmlEnvioManifestacao.getBytes());
									documentoXml.setData_gravacao(LocalDateTime.now());
									documentoXmlDAO.save(documentoXml);
									
									documentoXml = new DocumentoXml();
									nfeRecebimento = new NfeRecebimento();
									
									documentoXml.setTipo("XML de retorno manifestação do destinatário");
									documentoXml.setXml(xmlRetornoManifestacao.getBytes());
									documentoXml.setData_gravacao(LocalDateTime.now());
									documentoXmlDAO.save(documentoXml);
									System.out.println("XML manifestação salvo com sucesso!");
								}
								
							}
							break;
						case "resEvento_v1.01.xsd" :
							ResEvento resEvento = XmlUtil.xmlToObject(XmlUtil.gZipToXml(docZip.getValue()), ResEvento.class);
							//TODO tratar eventos NF-e
							break;
						default:
							break;
						}
					}
					
					if(Long.valueOf(retorno.getUltNSU()) < Long.valueOf(retorno.getMaxNSU())){
						System.out.println("==================================");
						System.out.println("Verificando sequencia de NSU");
						System.out.println("==================================");
						//distNSU.setUltNSU(retorno.getUltNSU());
						//distDFeInt.setDistNSU(distNSU);
						consultaDocumentos(empresa, Long.valueOf(retorno.getUltNSU()));
					}else if(Long.valueOf(retorno.getUltNSU()).equals(Long.valueOf(retorno.getMaxNSU())) && !retorno.getCStat().equals("656")){
						controleNFe.setEmpresaId(empresa.getId());
						controleNFe.setNsu(Long.valueOf(retorno.getMaxNSU()));
						controleNFe.setStatus(Integer.valueOf(retorno.getCStat()));
						controleNFe.setTipoAmbiente(empresa.getAmbiente());
						controleNFe.setData_ultima_consulta(LocalDateTime.now());
						controleNFe.setAplicacao("nfe");
						controleService.salvar(controleNFe);
						System.out.println("Controle de WS e NSU salvo com sucesso!");
					}
				}else if(retorno.getCStat().equals("108") || retorno.getCStat().equals("109")){ // 108 - Verifica se o Servidor de Processamento está Paralisado Momentaneamente 
																					  // 109 - Verifica se o Servidor de Processamento está Paralisado sem Previsão
				}else if(retorno.getCStat().equals("656")){ // Consumo Indevido
					System.out.println(retorno.getCStat().concat(" - ".concat(retorno.getXMotivo()).concat(LocalDateTime.now().toString())));
					Thread.sleep(360000);
				}
		//	}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public Long buscaUltNsu(Empresa empresa){
		Long ultimoNsu = controleService.getMaxNsuByEmpresaIdAndTipoAmbiente(empresa, empresa.getAmbiente());
		if (ultimoNsu == null) {
			System.out.println("Ainda não existe controle de NSU para essa empresa, informando '000000000000000'!!!");
			return 0L;
		}
		System.out.println("NSU " + ultimoNsu + " encontrado para empresa "+ empresa.getCnpj());
		return ultimoNsu;
	}
	
	public static String cienciaOperacao(ResNFe resNFe, String cnpj) {
		
		ConfiguracoesIniciaisNfe config = null;
		try {
			config = ConfiguracoesIniciaisNfe.getInstance();
		} catch (NfeException e1) {
			System.out.println("Erro ao obter a configuração de empresa");
			e1.printStackTrace();
		}
		
		TEnvEvento manifestacao = new TEnvEvento();
		TEvento evento = new TEvento();
		TEvento.InfEvento infEvento = new TEvento.InfEvento();
		
		TEvento.InfEvento.DetEvento det = new TEvento.InfEvento.DetEvento(); //ciencia da operacao
			det.setDescEvento("Ciencia da Operacao");
			det.setVersao("1.00");
			//det.setXJust("");
		
		
		
		manifestacao.setVersao("1.00");
		manifestacao.setIdLote("1");
		infEvento.setChNFe(resNFe.getChNFe());
		infEvento.setCNPJ(cnpj);
		infEvento.setCOrgao("91");
		infEvento.setDhEvento(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss" + ZonedDateTime.now().getOffset()).withZone(ZoneId.of("UTC"))));
		infEvento.setId("ID"+ CIENCIA_DA_OPERACO + resNFe.getChNFe() + "01");
		infEvento.setTpAmb(config.getAmbiente());
		infEvento.setTpEvento(CIENCIA_DA_OPERACO);
		infEvento.setVerEvento("1.00");
		infEvento.setNSeqEvento("1");
		
		infEvento.setDetEvento(det);
		
		evento.setInfEvento(infEvento);
		evento.setVersao("1.00");
		
		manifestacao.getEvento().add(evento);
		
		try {
			xmlEnvioManifestacao = XmlUtil.objectToXml(manifestacao);
		
			br.inf.portalfiscal.nfe.retEnvConfRecebto_v100.TRetEnvEvento result = Evento.eventoManifestacao(manifestacao, false);
			xmlRetornoManifestacao = XmlUtil.objectToXml(result);
			System.out.println("XMotivo: " + result.getXMotivo());
			System.out.println("Status: " + result.getCStat());
			if(result.getCStat().equals("128")){
				System.out.println("NProt: " + result.getRetEvento().get(0).getInfEvento().getNProt());
				System.out.println("Resultado getCStat: " + result.getRetEvento().get(0).getInfEvento().getCStat());
				System.out.println("Resultado getXMotivo: " + result.getRetEvento().get(0).getInfEvento().getXMotivo());
			}
			if(result.getCStat() != "128"){
				System.out.println("Erro nos dados informados!");
			}else if(result.getCStat().equals("135") || result.getCStat().equals("136")){
				return xmlRetornoManifestacao;
			}
			
			return null;
		} catch (NfeException | JAXBException e) {
			e.printStackTrace();
			return null;
		}
		
	}
	
	
	
}
