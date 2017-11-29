package br.com.prox.nfe;

import java.rmi.RemoteException;

import javax.xml.bind.JAXBException;
import javax.xml.stream.XMLStreamException;

import org.apache.axiom.om.OMElement;
import org.apache.axiom.om.util.AXIOMUtil;

import br.com.prox.nfe.certificado.Certificado;
import br.com.prox.nfe.exception.NfeException;
import br.com.prox.nfe.exception.NfeValidacaoException;
import br.com.prox.nfe.util.CertificadoUtil;
import br.com.prox.nfe.util.ConstantesUtil;
import br.com.prox.nfe.util.Estados;
import br.com.prox.nfe.util.ObjetoUtil;
import br.com.prox.nfe.util.WebServiceUtil;
import br.com.prox.nfe.util.XmlUtil;
import br.inf.portalfiscal.nfe.conscad.TConsCad;
import br.inf.portalfiscal.nfe.conscad.TConsCad.InfCons;
import br.inf.portalfiscal.nfe.conscad.TRetConsCad;
import br.inf.portalfiscal.nfe.conscad.TRetConsCad.InfCons.InfCad;
import br.inf.portalfiscal.nfe.conscad.TUfCons;
import ch.qos.logback.core.joran.spi.XMLUtil;
import webservice.nfe.CadConsultaCadastro2Stub;
import webservice.nfe.CadConsultaCadastro2Stub.ConsultaCadastro2Result;

/**
 * Classe responsavel por Consultar a Situaçao do XML na SEFAZ.
 * 
 * @author Samuel Oliveira - samuk.exe@hotmail.com - www.samuelweb.com.br
 * 
 */

public class ConsultaCadastro {

	private static ConsultaCadastro2Result result;
	private static CertificadoUtil certUtil;

	
	public static void main(String[] args){
		try {
			ConfiguracoesIniciaisNfe config = iniciaConfigurações();
			
			config.setEstado(Estados.SP);
			
			//downloadChave();
			//statusServico();
			TConsCad consCad = new TConsCad();
			InfCons info = new InfCons();
			
			info.setCNPJ("01554976000250");
			//info.setIE("13907011000100");
			
			info.setUF(TUfCons.SP);
			info.setXServ("CONS-CAD");
			
			consCad.setInfCons(info);
			consCad.setVersao("2.00");
			
			TRetConsCad cad = consultaCadastro(consCad, true);

			System.out.println("MOTIVO: " + cad.getInfCons().getCStat() + " - " + cad.getInfCons().getXMotivo());
			for(InfCad inf: cad.getInfCons().getInfCad()){
				System.out.println("Razão Social: " + inf.getXNome());
				System.out.println("CNPJ: " + inf.getCNPJ());
				System.out.println("IE: " + inf.getIE());
				System.out.println("Regime: " + inf.getXRegApur());
				System.out.println("CEP: " + inf.getEnder().getCEP());
			}
			
			//ConsultaCadastro.cad();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public static ConfiguracoesIniciaisNfe iniciaConfigurações() throws NfeException {
		// Certificado Windows
		// Certificado certificado = CertificadoUtil.listaCertificadosWindows().get(0);
		
		// Certificado PFX
		Certificado certificado = CertificadoUtil.certificadoPfx("D:\\Projetos MasterSAF DF-e\\Oracle\\DFE\\Certificado2017\\CertificadoOracle2017.pfx", "Oracle0819");
			
		return ConfiguracoesIniciaisNfe.iniciaConfiguracoes(Estados.SP , ConstantesUtil.AMBIENTE.HOMOLOGACAO,
				certificado, "D:\\Java\\livrariaJava\\Schemas_Java_NFe", ConstantesUtil.VERSAO.V3_10);
	}
	
	/**
	 * Classe Reponsavel Por Consultar o status da NFE na SEFAZ
	 * 
	 * @param consCad
	 * @param valida
	 * @return
	 * @throws NfeException
	 */
	
	public static TRetConsCad consultaCadastro(TConsCad consCad, boolean valida) throws NfeException {

		certUtil = new CertificadoUtil();

		try {

			certUtil.iniciaConfiguracoes();

			String xml = XmlUtil.objectToXml(consCad);

			if (valida) {
				String erros = Validar.validaXml(xml, Validar.CONSULTA_CADASTRO);
				if (!ObjetoUtil.isEmpty(erros)) {
					throw new NfeValidacaoException("Erro Na Validação do Xml: " + erros);
				}
			}

			System.out.println("Xml Consulta: " + xml);
			OMElement ome = AXIOMUtil.stringToOM(xml);

			CadConsultaCadastro2Stub.NfeDadosMsg dadosMsg = new CadConsultaCadastro2Stub.NfeDadosMsg();
			dadosMsg.setExtraElement(ome);

			CadConsultaCadastro2Stub.NfeCabecMsg nfeCabecMsg = new CadConsultaCadastro2Stub.NfeCabecMsg();
			nfeCabecMsg.setCUF(Estados.valueOf(consCad.getInfCons().getUF().toString()).getCodigoIbge());
			nfeCabecMsg.setVersaoDados("2.00");

			CadConsultaCadastro2Stub.NfeCabecMsgE nfeCabecMsgE = new CadConsultaCadastro2Stub.NfeCabecMsgE();
			nfeCabecMsgE.setNfeCabecMsg(nfeCabecMsg);

			CadConsultaCadastro2Stub stub = new CadConsultaCadastro2Stub(WebServiceUtil.getUrlConsultaCadastro(consCad.getInfCons().getUF().toString()));
			result = stub.consultaCadastro2(dadosMsg, nfeCabecMsgE);
			
			return XmlUtil.xmlToObject(result.getExtraElement().toString(), TRetConsCad.class);

		} catch (RemoteException | XMLStreamException | JAXBException e) {
			throw new NfeException(e.getMessage());
		}

	}

}
