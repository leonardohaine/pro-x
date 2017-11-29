package br.com.prox.service;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.ZonedDateTime;

import javax.xml.bind.JAXBException;

import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.prox.model.DocumentoXml;
import br.com.prox.model.NfeRecebimento;
import br.com.prox.nfe.Validar;
import br.com.prox.nfe.exception.NfeException;
import br.com.prox.nfe.util.XmlUtil;
import br.com.prox.repository.NfeRecebimentoDAO;
import br.com.prox.util.FacesMessages;
import br.inf.portalfiscal.nfe.envEventoCancNFe_v1.TEvento;
import br.inf.portalfiscal.nfe.envEventoCancNFe_v1.TProcEvento;
import br.inf.portalfiscal.nfe.envEventoCancNFe_v1.TRetEvento;
import br.inf.portalfiscal.nfe.nfe.TNFe;
import br.inf.portalfiscal.nfe.nfe.TNfeProc;
import br.inf.portalfiscal.nfe.retDistDFeInt_v1.RetDistDFeInt.LoteDistDFeInt.DocZip;


@Service
public class NfeRecebimentoService {
	
	@Autowired
	private NfeRecebimentoDAO nfeRecebimentoDao;
	
	
	@Autowired
	private FacesMessages messages;
	
	@Transactional
	public void salvar(NfeRecebimento nfeRecebimento, DocumentoXml documentoXml, DocZip docZip){
		
		TNfeProc nfeProc = null;
		try {
			nfeProc = XmlUtil.xmlToObject(XmlUtil.gZipToXml(docZip.getValue()), TNfeProc.class);
		} catch (JAXBException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		//Validando o XML
		String validacao =  null;
		try {
				validacao = Validar.validaXml(XmlUtil.gZipToXml(docZip.getValue()), "nfeProc");
				nfeRecebimento.setNfe_doc_xml_emissao_id(documentoXml.getId());
		} catch (NfeException | IOException e) {
			new Exception("Erro na validação do XML");
		} 
		
		TNFe.InfNFe.Emit emit = nfeProc.getNFe().getInfNFe().getEmit();
		TNFe.InfNFe.Dest dest = nfeProc.getNFe().getInfNFe().getDest();
		TNFe.InfNFe.Ide ide = nfeProc.getNFe().getInfNFe().getIde();
		TNFe.InfNFe.Total total = nfeProc.getNFe().getInfNFe().getTotal();
		
		nfeRecebimento.setDescricao_status(validacao != null ? nfeProc.getProtNFe().getInfProt().getXMotivo() : validacao);
		nfeRecebimento.setAssinatura_invalida_emissao(!validacao.isEmpty());
		
		nfeRecebimento.setCnpjEmpresa(dest.getCNPJ());
		nfeRecebimento.setIe_empresa(dest.getIE());
		nfeRecebimento.setCnpj_remetente(emit.getCNPJ());
		nfeRecebimento.setRazao_social_remetente(emit.getXNome());
		nfeRecebimento.setUf_remetente(emit.getEnderEmit().getUF().value());
		nfeRecebimento.setChave(nfeProc.getProtNFe().getInfProt().getChNFe());
		nfeRecebimento.setTipo_ambiente_sistema(ide.getTpAmb());
		nfeRecebimento.setTipo_emissao(ide.getTpEmis());
		nfeRecebimento.setEntrada_saida(Integer.valueOf(ide.getTpNF()));
		nfeRecebimento.setNumero_nfe(Long.valueOf(ide.getNNF()));
		nfeRecebimento.setSerie(ide.getSerie());
		nfeRecebimento.setVersao(nfeProc.getNFe().getInfNFe().getVersao());
		nfeRecebimento.setStatus(Integer.valueOf(nfeProc.getProtNFe().getInfProt().getCStat()));
		nfeRecebimento.setNumero_protocolo_emissao(nfeProc.getProtNFe().getInfProt().getNProt());
		nfeRecebimento.setTipo_documento("nfe");
		nfeRecebimento.setTipo_xml("NF-e");
		nfeRecebimento.setDownload_xml(Boolean.TRUE);
		nfeRecebimento.setData_emissao(ZonedDateTime.parse(ide.getDhEmi()).toLocalDateTime());
		nfeRecebimento.setData_autorizacao(ZonedDateTime.parse(nfeProc.getProtNFe().getInfProt().getDhRecbto()).toLocalDateTime());
		nfeRecebimento.setDigest_value_emissao(Base64.encodeBase64String(nfeProc.getProtNFe().getInfProt().getDigVal()));
		nfeRecebimento.setValor(new BigDecimal(total.getICMSTot().getVNF()));
		
		nfeRecebimento.setNsu(Long.valueOf(docZip.getNSU()));
		
		nfeRecebimentoDao.save(nfeRecebimento);
	}

	@Transactional
	public void salvarCancelamento(NfeRecebimento nfeRecebimento, DocumentoXml documentoXml, DocZip docZip){
		
		TProcEvento procEvento = null;
		try {
			procEvento = XmlUtil.xmlToObject(XmlUtil.gZipToXml(docZip.getValue()), TProcEvento.class);
		} catch (JAXBException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		//Validando o XML
		String validacao =  null;
		try {
			validacao = Validar.validaXml(XmlUtil.gZipToXml(docZip.getValue()), "cancelar");
		} catch (NfeException | IOException e) {
			new Exception("Erro na validação do XML de cancelamento");
		}
		
		TEvento.InfEvento evento  = procEvento.getEvento().getInfEvento();
		TRetEvento.InfEvento retEvento = procEvento.getRetEvento().getInfEvento();
		TEvento.InfEvento.DetEvento detEvento = procEvento.getEvento().getInfEvento().getDetEvento();
		
		
		nfeRecebimento.setCnpj_remetente(evento.getCNPJ());
		nfeRecebimento.setCnpjEmpresa(retEvento.getCNPJDest());
		nfeRecebimento.setData_cancelmaento(ZonedDateTime.parse(evento.getDhEvento()).toLocalDateTime());
		nfeRecebimento.setNumero_protocolo_canc(retEvento.getNProt());
		nfeRecebimento.setCancelamento_sistema(Boolean.FALSE);
		nfeRecebimento.setDigest_value_canc(Base64.encodeBase64String(procEvento.getEvento().getSignature().getSignedInfo().getReference().getDigestValue()));
		nfeRecebimento.setTipo_ambiente_sistema(evento.getTpAmb());
		nfeRecebimento.setCodigo_evento(Integer.valueOf(evento.getTpEvento()));
		nfeRecebimento.setUf_remetente(evento.getCOrgao());
		
		nfeRecebimento.setTipo_documento("nfe");
		nfeRecebimento.setTipo_xml("NF-e cancelada");
		nfeRecebimento.setDownload_xml(Boolean.TRUE);
		
		nfeRecebimento.setNsu(Long.valueOf(docZip.getNSU()));
		nfeRecebimento.setNfe_doc_xml_cancelamento_id(documentoXml.getId());
		
		nfeRecebimentoDao.save(nfeRecebimento);
	}
	
	@Transactional
	public void excluir(NfeRecebimento nfeRecebimento) {
		nfeRecebimentoDao.delete(nfeRecebimento);
	}
	
}
