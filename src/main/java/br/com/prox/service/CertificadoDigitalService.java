package br.com.prox.service;

import java.security.KeyStore;
import java.security.cert.X509Certificate;
import java.sql.Date;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;

import org.apache.axiom.om.util.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.prox.model.CertificadoDigital;
import br.com.prox.nfe.certificado.Certificado;
import br.com.prox.nfe.certificado.CertificadoService;
import br.com.prox.nfe.util.CertificadoUtil;
import br.com.prox.repository.CertificadoDigitalDAO;
import br.com.prox.repository.Empresas;
import br.com.prox.util.FacesMessages;


@Service
public class CertificadoDigitalService {
	
	@Autowired
	private CertificadoDigitalDAO certificadoDao;
	

	@Autowired
	private Empresas empresas;
	
	@Autowired
	CertificadoDigital certificado;
	
	@Autowired
	private FacesMessages messages;
	
	@Transactional
	public void salvar(byte[] certificadoBytes, String senhaCertificado){
		if(senhaCertificado == null || senhaCertificado.equals("")) {
			messages.warn("Senha do certificado não informada, campo obrigatório!");
			return;
		}
		            
        try{
        
        	//CertificadoDigital certificado = new CertificadoDigital();
	        Certificado c = CertificadoUtil.certificadoPfxBytes(certificadoBytes, senhaCertificado);
	        	
	        KeyStore keyStore = new CertificadoService().getKeyStore(c);
	        Enumeration<String> aliasEnum = keyStore.aliases();
	        String aliasKey = aliasEnum.nextElement();
	        X509Certificate cert = (X509Certificate) keyStore.getCertificate(aliasKey);
	        Map<String, String> dados = CertificadoUtil.getInfoAdicionais(cert);
	        
			certificado.setAlias(c.getNome().toUpperCase());
			certificado.setEmail(dados.get("email"));
			certificado.setPassword(Base64.encode(senhaCertificado.getBytes("UTF-8")));
			certificado.setKeyStoreBlob(c.getArquivoBytes());
			certificado.setSerialNumber(cert.getSerialNumber().toString());
			certificado.setData_emissao(Date.valueOf(c.getEmissao()));
			certificado.setData_expiracao(Date.valueOf(c.getVencimento()));
			certificado.setRaiz(dados.get("cnpj").substring(0, 8));
			certificado.setFilial(dados.get("cnpj").substring(8, 12));
			certificado.setDigitos(dados.get("cnpj").substring(12, 14));
			System.out.println("AlternativeNames: " + dados);
			
			String cnpj = pontuacaoCnpj(certificado.getRaiz().concat(certificado.getFilial().concat(certificado.getDigitos())));
			if (empresas.findByCnpj(cnpj) == null) {
				messages.error("CNPJ do certificado não cadastrado no sistema.<br/> "
						+ "Verifique o cadastro de empresas e se o certificado está correto.\n"
						+ "CNPJ do certificado: " + pontuacaoCnpj(certificado.getRaiz().concat(certificado.getFilial().concat(certificado.getDigitos()))));
				return;
			}else{
				certificadoDao.save(certificado);
				messages.info("Certificado digital salvo com sucesso!!!");
			}
			
        }catch (Exception e) {
			e.printStackTrace();
			messages.error("Erro ao tentar salvar certificado digital");
			messages.error(e.getMessage());
		}
	}
	
	public String pontuacaoCnpj(String cnpj){
		cnpj = (cnpj.substring(0, 2) + "." + 
			 	 cnpj.substring(2, 5) + "." +
			 	 cnpj.substring(5, 8) + "/" + 
			 	 cnpj.substring(8, 12) + "-" +
			 	 cnpj.substring(12, 14));
		
		return cnpj;
	}

	@Transactional
	public void excluir(CertificadoDigital certificado) {
		certificadoDao.delete(certificado);
	}
	
}
