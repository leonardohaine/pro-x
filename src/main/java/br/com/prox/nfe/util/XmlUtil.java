/**
 * 
 */
package br.com.prox.nfe.util;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.io.StringWriter;
import java.text.Normalizer;
import java.util.zip.GZIPInputStream;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.stream.StreamSource;

import br.com.prox.nfe.exception.NfeException;
import br.inf.portalfiscal.nfe.confRecebto_v100.TRetEnvEvento;
import br.inf.portalfiscal.nfe.conscad.TConsCad;
import br.inf.portalfiscal.nfe.consreci.TConsReciNFe;
import br.inf.portalfiscal.nfe.conssit.TConsSitNFe;
import br.inf.portalfiscal.nfe.consstatserv.TConsStatServ;
import br.inf.portalfiscal.nfe.distDFeInt_v1.DistDFeInt;
import br.inf.portalfiscal.nfe.envEventoCancNFe_v1.TEnvEvento;
import br.inf.portalfiscal.nfe.envi.TEnviNFe;
import br.inf.portalfiscal.nfe.envi.TNfeProc;
import br.inf.portalfiscal.nfe.envi.TProtNFe;
import br.inf.portalfiscal.nfe.inut.TInutNFe;

/**
 * Classe Responsavel por Metodos referentes ao XML
 * 
 * @author Samuel Oliveira
 *
 */
public class XmlUtil {

	private static final String STATUS = "TConsStatServ";
	private static final String SITUACAO_NFE = "TConsSitNFe";
	private static final String ENVIO_NFE = "TEnviNFe";
	private static final String DIST_DFE = "DistDFeInt";
	private static final String INUTILIZACAO = "TInutNFe";
	private static final String NFEPROC = "TNfeProc";
	private static final String EVENTO = "TEnvEvento";
	private static final String RETEVENTO = "TRetEnvEvento";
	private static final String TPROCEVENTO = "TProcEvento";
	private static final String TCONSRECINFE = "TConsReciNFe";
	private static final String TConsCad = "TConsCad";

	private static final String TPROCCANCELAR = "br.inf.portalfiscal.nfe.envEventoCancNFe.TProcEvento";
	private static final String TPROCCCE = "br.inf.portalfiscal.nfe.envcce.TProcEvento";

	private static final String TProtNFe = "TProtNFe";
	private static final String TProtEnvi = "br.inf.portalfiscal.nfe.envi.TProtNFe";
	private static final String TProtCons = "br.inf.portalfiscal.nfe.retconssit.TRetConsSitNFe";
	private static final String TProtReci = "br.inf.portalfiscal.nfe.retconsreci.TRetConsReciNFe";

	private static final String CANCELAR = "br.inf.portalfiscal.nfe.envEventoCancNFe.TEnvEvento";
	private static final String CCE = "br.inf.portalfiscal.nfe.envcce.TEnvEvento";
	private static final String MANIFESTAR = "br.inf.portalfiscal.nfe.envConfRecebto_v100.TEnvEvento";

	/**
	 * Transforma o String do XML em Objeto
	 * 
	 * @param xml
	 * @param classe
	 * @return T
	 */
	public static <T> T xmlToObject(String xml, Class<T> classe) throws JAXBException {

		JAXBContext context = JAXBContext.newInstance(classe);
		Unmarshaller unmarshaller = context.createUnmarshaller();

		return unmarshaller.unmarshal(new StreamSource(new StringReader(xml)), classe).getValue();
	}

	/**
	 * Transforma o Objeto em XML(String)
	 * 
	 * @param obj
	 * @return
	 * @throws JAXBException
	 * @throws NfeException
	 */
	public static <T> String objectToXml(Object obj) throws JAXBException, NfeException {

		JAXBContext context = null;
		JAXBElement<?> element = null;

		switch (obj.getClass().getSimpleName()) {

		case STATUS:
			context = JAXBContext.newInstance(TConsStatServ.class);
			element = new br.inf.portalfiscal.nfe.consstatserv.ObjectFactory().createConsStatServ((TConsStatServ) obj);
			break;

		case ENVIO_NFE:
			context = JAXBContext.newInstance(TEnviNFe.class);
			element = new br.inf.portalfiscal.nfe.envi.ObjectFactory().createEnviNFe((TEnviNFe) obj);
			break;

		case SITUACAO_NFE:
			context = JAXBContext.newInstance(TConsSitNFe.class);
			element = new br.inf.portalfiscal.nfe.conssit.ObjectFactory().createConsSitNFe((TConsSitNFe) obj);
			break;

		case DIST_DFE:
			context = JAXBContext.newInstance(DistDFeInt.class);
			element = new br.inf.portalfiscal.nfe.distDFeInt_v1.ObjectFactory().createDistDFeInt((DistDFeInt)obj);
			break;

		case TCONSRECINFE:
			context = JAXBContext.newInstance(TConsReciNFe.class);
			element = new br.inf.portalfiscal.nfe.consreci.ObjectFactory().createConsReciNFe((TConsReciNFe) obj);
			break;

		case TConsCad:
			context = JAXBContext.newInstance(TConsCad.class);
			element = new br.inf.portalfiscal.nfe.conscad.ObjectFactory().createConsCad((TConsCad) obj);
			break;
		
		case INUTILIZACAO:
			context = JAXBContext.newInstance(TInutNFe.class);
			element = new br.inf.portalfiscal.nfe.inut.ObjectFactory().createInutNFe((TInutNFe) obj);
			break;

		case TPROCEVENTO:
			if (obj.getClass().getName().equals(TPROCCANCELAR)) {
				context = JAXBContext.newInstance(br.inf.portalfiscal.nfe.eventoCancNFe_v1.TProcEvento.class);
				element = new br.inf.portalfiscal.nfe.procEventoCancNFe_v1.ObjectFactory().createProcEventoNFe((br.inf.portalfiscal.nfe.procEventoCancNFe_v1.TProcEvento) obj);
			} else if (obj.getClass().getName().equals(TPROCCCE)) {
				context = JAXBContext.newInstance(br.inf.portalfiscal.nfe.envCCe_v1.TProcEvento.class);
				element = new br.inf.portalfiscal.nfe.procCCeNFe_v1.ObjectFactory().createProcEventoNFe((br.inf.portalfiscal.nfe.procCCeNFe_v1.TProcEvento) obj);
			}

			break;

		case NFEPROC:
			context = JAXBContext.newInstance(TNfeProc.class);
			element = new br.inf.portalfiscal.nfe.proc.ObjectFactory().createNfeProc((br.inf.portalfiscal.nfe.proc.TNfeProc) obj);
			break;

		case EVENTO:
			if (obj.getClass().getName().equals(CANCELAR)) {
				context = JAXBContext.newInstance(br.inf.portalfiscal.nfe.eventoCancNFe_v1.TEnvEvento.class);
				element = new br.inf.portalfiscal.nfe.envEventoCancNFe_v1.ObjectFactory().createEnvEvento((TEnvEvento) obj);
			} else if (obj.getClass().getName().equals(CCE)) {
				context = JAXBContext.newInstance(br.inf.portalfiscal.nfe.envCCe_v1.TEnvEvento.class);
				element = new br.inf.portalfiscal.nfe.envCCe_v1.ObjectFactory().createEnvEvento((br.inf.portalfiscal.nfe.envCCe_v1.TEnvEvento) obj);
			} else if (obj.getClass().getName().equals(MANIFESTAR)) {
				context = JAXBContext.newInstance(br.inf.portalfiscal.nfe.envConfRecebto_v100.TEnvEvento.class);
				element = new br.inf.portalfiscal.nfe.envConfRecebto_v100.ObjectFactory().createEnvEvento((br.inf.portalfiscal.nfe.envConfRecebto_v100.TEnvEvento) obj);
			}
			break;
		
		case RETEVENTO:
			context = JAXBContext.newInstance(br.inf.portalfiscal.nfe.retEnvConfRecebto_v100.TRetEnvEvento.class);
			element = new br.inf.portalfiscal.nfe.retEnvConfRecebto_v100.ObjectFactory().createRetEnvEvento((br.inf.portalfiscal.nfe.retEnvConfRecebto_v100.TRetEnvEvento) obj);
			
			break;

		case TProtNFe:
			if (obj.getClass().getName().equals(TProtEnvi)) {
				context = JAXBContext.newInstance(TProtNFe.class);
				element = new br.inf.portalfiscal.nfe.envi.ObjectFactory().createEnviNFe((br.inf.portalfiscal.nfe.envi.TEnviNFe) obj);
			} else if (obj.getClass().getName().equals(TProtCons)) {
				context = JAXBContext.newInstance(br.inf.portalfiscal.nfe.retconssit.TProtNFe.class);
				element = new br.inf.portalfiscal.nfe.retconssit.ObjectFactory().createRetConsSitNFe((br.inf.portalfiscal.nfe.retconssit.TRetConsSitNFe) obj);
			} else if (obj.getClass().getName().equals(TProtReci)) {
				context = JAXBContext.newInstance(br.inf.portalfiscal.nfe.retconsreci.TProtNFe.class);
				element = new br.inf.portalfiscal.nfe.retconsreci.ObjectFactory().createRetConsReciNFe((br.inf.portalfiscal.nfe.retconsreci.TRetConsReciNFe) obj);
			}
			break;

		default:
			throw new NfeException("Objeto não mapeado no XmlUtil:" + obj.getClass().getSimpleName());
		}
		Marshaller marshaller = context.createMarshaller();

		marshaller.setProperty("jaxb.encoding", "Unicode");
		marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.FALSE);
		marshaller.setProperty(Marshaller.JAXB_FRAGMENT, Boolean.TRUE);

		StringWriter sw = new StringWriter();

		if(obj.getClass().getSimpleName().equals(ENVIO_NFE) || obj.getClass().getSimpleName().equals(NFEPROC)){
			try {
				CDATAContentHandler cdataHandler = new CDATAContentHandler(sw, "utf-8");
				marshaller.marshal(element, cdataHandler);
			} catch (IOException e) {
				throw new NfeException(e.getMessage());
			}
		}else{
			marshaller.marshal(element, sw);
		}
		StringBuilder xml = new StringBuilder();
		xml.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>").append(sw.toString());

		if ((obj.getClass().getSimpleName().equals(TPROCEVENTO))) {
			return replacesNfe(xml.toString().replaceAll("procEvento", "procEventoNFe"));
		} else {
			return replacesNfe(xml.toString());
		}

	}

	public static String gZipToXml(byte[] conteudo) throws IOException {
		if (conteudo == null || conteudo.length == 0) {
			return "";
		}
		GZIPInputStream gis;
		gis = new GZIPInputStream(new ByteArrayInputStream(conteudo));
		BufferedReader bf = new BufferedReader(new InputStreamReader(gis, "UTF-8"));
		String outStr = "";
		String line;
		while ((line = bf.readLine()) != null) {
			outStr += line;
		}

		return outStr;
	}

	public static String criaNfeProc(TEnviNFe enviNfe, Object retorno) throws JAXBException, NfeException {

		TNfeProc nfeProc = new TNfeProc();
		nfeProc.setVersao("3.10");
		nfeProc.setNFe(enviNfe.getNFe().get(0));
		String xml = XmlUtil.objectToXml(retorno);
		nfeProc.setProtNFe(XmlUtil.xmlToObject(xml, TProtNFe.class));

		String xmlFinal = XmlUtil.objectToXml(nfeProc);

		return xmlFinal;
	}

	public static String removeAcentos(String str) {

		str = str.replaceAll("\r", "");
		str = str.replaceAll("\t", "");
		str = str.replaceAll("\n", "");
		str = str.replaceAll("&", "E");
		str = str.replaceAll(">\\s+<", "><");
		CharSequence cs = new StringBuilder(str == null ? "" : str);
		return Normalizer.normalize(cs, Normalizer.Form.NFKD).replaceAll("\\p{InCombiningDiacriticalMarks}+", "");

	}

	public static String replacesNfe(String xml) {

		xml = xml.replaceAll("ns2:", "");
		xml = xml.replaceAll("<!\\[CDATA\\[<!\\[CDATA\\[", "<!\\[CDATA\\[");
		xml = xml.replaceAll("\\]\\]>\\]\\]>", "\\]\\]>");
		xml = xml.replaceAll("ns3:", "");
		xml = xml.replaceAll("&lt;", "<");
		xml = xml.replaceAll("&amp;", "&");
		xml = xml.replaceAll("&gt;", ">");
		xml = xml.replaceAll("<Signature>", "<Signature xmlns=\"http://www.w3.org/2000/09/xmldsig#\">");
		xml = xml.replaceAll(" xmlns:ns2=\"http://www.w3.org/2000/09/xmldsig#\"", "");
		xml = xml.replaceAll(" xmlns=\"\" xmlns:ns3=\"http://www.portalfiscal.inf.br/nfe\"", "");

		return xml;

	}

	/**
	 * Le o Arquivo XML e retona String
	 * 
	 * @return String
	 * @throws NfeException
	 */
	public static String leXml(String arquivo) throws NfeException {

		StringBuilder xml = new StringBuilder();
		BufferedReader in;
		try {
			in = new BufferedReader(new InputStreamReader(new FileInputStream(arquivo), "UTF-8"));
			String linha;

			while ((linha = in.readLine()) != null) {
				xml.append(linha);

			}
			in.close();
		} catch (IOException e) {
			throw new NfeException("Ler Xml: " + e.getMessage());
		}
		return xml.toString();
	}

}
