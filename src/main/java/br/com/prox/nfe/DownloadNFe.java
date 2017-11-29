package br.com.prox.nfe;

import java.io.IOException;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.geronimo.mail.util.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.prox.model.CertificadoDigital;
import br.com.prox.model.Empresa;
import br.com.prox.nfe.certificado.Certificado;
import br.com.prox.nfe.exception.NfeException;
import br.com.prox.nfe.util.CertificadoUtil;
import br.com.prox.nfe.util.ConstantesUtil;
import br.com.prox.nfe.util.Estados;
import br.com.prox.nfe.util.XmlUtil;
import br.com.prox.repository.CertificadoDigitalDAO;
import br.inf.portalfiscal.nfe.distDFeInt_v1.DistDFeInt;
import br.inf.portalfiscal.nfe.distDFeInt_v1.DistDFeInt.DistNSU;
import br.inf.portalfiscal.nfe.retDistDFeInt_v1.RetDistDFeInt;
import br.inf.portalfiscal.nfe.retDistDFeInt_v1.RetDistDFeInt.LoteDistDFeInt.DocZip;

@Component
public class DownloadNFe {
	
	@Autowired
	private CertificadoDigitalDAO certificadoDAO;
	
	public static void main(String[] args){
		//downloadNFeNSU("01554976000250", "000000000000204");
	}
	
	public RetDistDFeInt downloadNFeNSU(Empresa empresa, Long ultNSU){
		try{
			//Inicia As Configurações
			//Para Mais informações: https://github.com/Samuel-Oliveira/Java_NFe/wiki/Configura%C3%A7%C3%B5es-Nfe
			ConfiguracoesIniciaisNfe config = iniciaConfigurações(empresa);
			
			DistDFeInt distDFeInt = new DistDFeInt();
			distDFeInt.setVersao("1.01");
			distDFeInt.setTpAmb(config.getAmbiente());
			distDFeInt.setCUFAutor(config.getEstado().getCodigoIbge());
			//Substitua os X pelo CNPJ
			distDFeInt.setCNPJ(empresa.getCnpj().replaceAll("[^0-9]", ""));
			
			DistNSU distNSU = new DistNSU();
			// Ultilize NSU 0 para pegar todas as Notas, depois troque pelo Ultimo NSU
			distNSU.setUltNSU(StringUtils.leftPad(ultNSU.toString(), 15, "0"));
			distDFeInt.setDistNSU(distNSU);
			
			//Informe false para não fazer a validação do XML - Ganho De tempo.
			Long inicio = System.currentTimeMillis();
			RetDistDFeInt retorno = Nfe.distribuicaoDfe(distDFeInt,false);
			System.out.println("Tempo de execução da consulta no WS Distribuicao: " + (System.currentTimeMillis() - inicio) + " milisegundos");
			return retorno;
			
		
		} catch (NfeException e) {
			System.out.println("Erro:" + e.getMessage());
		}
		return null;
	}
	
	public ConfiguracoesIniciaisNfe iniciaConfigurações(Empresa empresa) throws NfeException {
		System.out.println("Carregando Configuração...");
		CertificadoDigital c = certificadoDAO.findByRaiz(empresa.getCnpj().replaceAll("[^0-9]", "").substring(0, 8));
		
		// Certificado PFX
		Certificado certificado = null;
		if(c != null){
			certificado = CertificadoUtil.certificadoPfxBytes(c.getKeyStoreBlob(), new String(Base64.decode(c.getPassword())));
		}else{
			System.out.println("Certificado não encontrado para essa empresa...");
			
		}
		return ConfiguracoesIniciaisNfe.iniciaConfiguracoes(Estados.valueOf(empresa.getUf()) , empresa.getAmbiente(),
				certificado, ConstantesUtil.RESOURCE_SCHEMA_NFE, empresa.getVersaoNfe());
	}
	
}
