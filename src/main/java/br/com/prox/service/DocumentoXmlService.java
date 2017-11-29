package br.com.prox.service;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.prox.model.DocumentoXml;
import br.com.prox.nfe.util.XmlUtil;
import br.com.prox.repository.DocumentoXmlDAO;
import br.com.prox.repository.Empresas;
import br.com.prox.util.FacesMessages;
import br.inf.portalfiscal.nfe.retDistDFeInt_v1.RetDistDFeInt.LoteDistDFeInt.DocZip;


@Service
public class DocumentoXmlService {
	
	@Autowired
	private DocumentoXmlDAO DocumentoXmlDao;
	

	@Autowired
	private Empresas empresas;
	
	
	@Autowired
	private FacesMessages messages;
	
	@Transactional
	public DocumentoXml salvar(DocumentoXml documentoXml, DocZip docZip){
		try {
			documentoXml.setTipo("XML de recebimento de NF-e");
			documentoXml.setDocumento("nf-e");
			documentoXml.setXml(XmlUtil.gZipToXml(docZip.getValue()).getBytes("UTF-8"));
			documentoXml.setData_gravacao(LocalDateTime.now());
			System.out.println("XML NF-e Recebimento salvo com sucesso!");
		} catch (IOException  e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return DocumentoXmlDao.save(documentoXml);
	}

	@Transactional
	public void excluir(DocumentoXml documentoXml) {
		DocumentoXmlDao.delete(documentoXml);
	}
	
}
