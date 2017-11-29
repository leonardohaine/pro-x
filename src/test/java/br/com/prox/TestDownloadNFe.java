package br.com.prox;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import org.apache.commons.codec.binary.Base64;

import br.com.prox.nfe.ConfiguracoesIniciaisNfe;
import br.com.prox.nfe.Nfe;
import br.com.prox.nfe.certificado.Certificado;
import br.com.prox.nfe.exception.NfeException;
import br.com.prox.nfe.util.CertificadoUtil;
import br.com.prox.nfe.util.ConstantesUtil;
import br.com.prox.nfe.util.Estados;
import br.com.prox.nfe.util.XmlUtil;
import br.inf.portalfiscal.nfe.distDFeInt_v1.DistDFeInt;
import br.inf.portalfiscal.nfe.distDFeInt_v1.DistDFeInt.ConsChNFe;
import br.inf.portalfiscal.nfe.distDFeInt_v1.DistDFeInt.DistNSU;
import br.inf.portalfiscal.nfe.retDistDFeInt_v1.RetDistDFeInt;
import br.inf.portalfiscal.nfe.retDistDFeInt_v1.RetDistDFeInt.LoteDistDFeInt.DocZip;

public class TestDownloadNFe {
	
	public static void main(String[] args){
		downloadNFeNSU("03147393000159", "000000000000000"); //000000000036816
		//downloadChave();
		//System.out.println("01.554.976/0002-50".replaceAll("[^0-9]", ""));
		//System.out.println(new String(Base64.decode("bXRicjIwMTY=".getBytes())));
		
		String xml = "<nfeProc versao=\"3.10\" xmlns=\"http://www.portalfiscal.inf.br/nfe\"><NFe xmlns=\"http://www.portalfiscal.inf.br/nfe\"><infNFe versao=\"3.10\" Id=\"NFe35171042123091004513550010000164541013576035\"><ide><cUF>35</cUF><cNF>01357603</cNF><natOp>Venda Producao do Estabelecimento - RFID</natOp><indPag>0</indPag><mod>55</mod><serie>1</serie><nNF>16454</nNF><dhEmi>2017-10-06T17:16:39-03:00</dhEmi><dhSaiEnt>2017-10-06T17:16:39-03:00</dhSaiEnt><tpNF>1</tpNF><idDest>1</idDest><cMunFG>3552205</cMunFG><tpImp>2</tpImp><tpEmis>1</tpEmis><cDV>5</cDV><tpAmb>2</tpAmb><finNFe>1</finNFe><indFinal>0</indFinal><indPres>9</indPres><procEmi>0</procEmi><verProc>3.10</verProc></ide><emit><CNPJ>42123091004513</CNPJ><xNome>INTERPRINT LTDA (SOROCABA/SP)</xNome><xFant>INTERPRINT (SOROCABA)</xFant><enderEmit><xLgr>RUA LAURA MAIELLO KOOK</xLgr><nro>511</nro><xCpl>BLOCO B PARTE</xCpl><xBairro>IPANEMA DAS PEDRAS</xBairro><cMun>3552205</cMun><xMun>SOROCABA</xMun><UF>SP</UF><CEP>18052445</CEP><cPais>1058</cPais><xPais>BRASIL</xPais><fone>1534126100</fone></enderEmit><IE>669615659110</IE><IM>000303299</IM><CNAE>1812100</CNAE><CRT>3</CRT></emit><dest><CNPJ>01554976000250</CNPJ><xNome>NF-E EMITIDA EM AMBIENTE DE HOMOLOGACAO - SEM VALOR FISCAL</xNome><enderDest><xLgr>AVENIDA TUCUNARE</xLgr><nro>720</nro><xCpl>BL-01</xCpl><xBairro>TAMBORE</xBairro><cMun>3505708</cMun><xMun>BARUERI</xMun><UF>SP</UF><CEP>06460020</CEP><cPais>1058</cPais><xPais>BRASIL</xPais></enderDest><indIEDest>1</indIEDest><IE>206088987115</IE></dest><det nItem=\"1\"><prod><cProd>213936100</cProd><cEAN /><xProd>BR ETIQUETA RFID RFID Tag CTC13002 -VLID2714</xProd><NCM>85437099</NCM><CFOP>5101</CFOP><uCom>UN</uCom><qCom>60000.0000</qCom><vUnCom>0.4800000000</vUnCom><vProd>28800.00</vProd><cEANTrib /><uTrib>UN</uTrib><qTrib>60000.0000</qTrib><vUnTrib>0.4800000000</vUnTrib><indTot>1</indTot></prod><imposto><ICMS><ICMS51><orig>4</orig><CST>51</CST><modBC>0</modBC></ICMS51></ICMS><IPI><cEnq>999</cEnq><IPITrib><CST>50</CST><vBC>28800.00</vBC><pIPI>2.0000</pIPI><vIPI>576.00</vIPI></IPITrib></IPI><PIS><PISAliq><CST>01</CST><vBC>28800.00</vBC><pPIS>1.6500</pPIS><vPIS>475.20</vPIS></PISAliq></PIS><COFINS><COFINSAliq><CST>01</CST><vBC>28800.00</vBC><pCOFINS>7.6000</pCOFINS><vCOFINS>2188.80</vCOFINS></COFINSAliq></COFINS></imposto></det><total><ICMSTot><vBC>0.00</vBC><vICMS>0.00</vICMS><vICMSDeson>0.00</vICMSDeson><vBCST>0.00</vBCST><vST>0.00</vST><vProd>28800.00</vProd><vFrete>0.00</vFrete><vSeg>0.00</vSeg><vDesc>0.00</vDesc><vII>0.00</vII><vIPI>576.00</vIPI><vPIS>475.20</vPIS><vCOFINS>2188.80</vCOFINS><vOutro>0.00</vOutro><vNF>29376.00</vNF></ICMSTot></total><transp><modFrete>0</modFrete></transp><cobr><fat><nFat>16454</nFat><vOrig>29376.00</vOrig><vLiq>29376.00</vLiq></fat><dup><nDup>4516454</nDup><dVenc>2017-11-05</dVenc><vDup>29376.00</vDup></dup></cobr><infAdic><infCpl>\"ICMS DIFERIDO CONFORME PORTARIA CAT-14 DE 12/02/2007\"</infCpl></infAdic></infNFe><Signature xmlns=\"http://www.w3.org/2000/09/xmldsig#\"><SignedInfo><CanonicalizationMethod Algorithm=\"http://www.w3.org/TR/2001/REC-xml-c14n-20010315\" /><SignatureMethod Algorithm=\"http://www.w3.org/2000/09/xmldsig#rsa-sha1\" /><Reference URI=\"#NFe35171042123091004513550010000164541013576035\"><Transforms><Transform Algorithm=\"http://www.w3.org/2000/09/xmldsig#enveloped-signature\" /><Transform Algorithm=\"http://www.w3.org/TR/2001/REC-xml-c14n-20010315\" /></Transforms><DigestMethod Algorithm=\"http://www.w3.org/2000/09/xmldsig#sha1\" /><DigestValue>BPcq9uRH0AwmjeBfMhJXVhSi+dI=</DigestValue></Reference></SignedInfo><SignatureValue>ITlUPxyvHk8BJJS/Mr7POFX+8JRlOVJvTWpGj1ziWDHU/Sl0CPLE0ZqJyRQgNhOU1e6d2rGcUwunOufMExuWSkpsx69FsY97xw/cWTCn6NG02pQcD4RBMKFgwNdFtrz3nHTktAJd/pEXz79SVydsyJ4rFSwQ1HISIY41bUSM73O5LJGkPaT8K1iIY3EV1Gl9uA1Puz64ikOzMmqpjA8XezjldlFJUhaQWL0Ur9l/EuDwJh/ryRRB7YVoICFmW+r/XWvw/VPvxB78vhYUsmAqykUi7sj2rqgO9UxIjM+SknKKsUI8qYGtiaX+b4UsxQSDynvgOFGZkD1wrK+nS7E7KQ==</SignatureValue><KeyInfo><X509Data><X509Certificate>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</X509Certificate></X509Data></KeyInfo></Signature></NFe><protNFe versao=\"3.10\"><infProt Id=\"Id135170004186151\"><tpAmb>2</tpAmb><verAplic>SP_NFE_PL_008i2</verAplic><chNFe>35171042123091004513550010000164541013576035</chNFe><dhRecbto>2017-10-06T17:22:13-03:00</dhRecbto><nProt>135170004186151</nProt><digVal>BPcq9uRH0AwmjeBfMhJXVhSi+dI=</digVal><cStat>100</cStat><xMotivo>Autorizado o uso da NF-e</xMotivo></infProt></protNFe></nfeProc>";
		System.out.println("Caminho: " + new File("src/main/resources/modelo/danfe/DANFE_MR.jasper").exists());
		/*try {
			ConfiguracoesIniciaisNfe config = iniciaConfigurações();
			System.out.println("Erros de validação: " + Validar.validaXml(xml, "nfeProc").isEmpty());
		} catch (NfeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		testeBytes();*/
		
		//2017-10-06T17:16:39-03:00
		
		String dhemi = "2017-10-06T17:16:39-03:00";
		ZonedDateTime d = ZonedDateTime.parse(dhemi);
		System.out.println(d.toLocalDateTime());
		
		
		//System.out.println(LocalDateTime.now().format(DateTimeFormatter.ISO_ZONED_DATE_TIME)); //DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss O").withZone(ZoneId.of("UTC"))));
		ZoneId fusoHorarioDeSaoPaulo = ZoneId.of("America/Sao_Paulo");
		ZoneOffset agoraEmSaoPaulo = ZonedDateTime.now().getOffset();
		System.out.println(agoraEmSaoPaulo); //2014-04-08T10:02:57.838-03:00[America/Sao_Paulo]
		System.out.println(LocalDateTime.now(agoraEmSaoPaulo.of("-02:00")));
		
		Date date = new Date();
		TimeZone zone = TimeZone.getTimeZone("America/Sao_Paulo");
		if( zone.inDaylightTime(date) ) {
		  // esta no horario de verão para "America/Sao_Paulo"
			System.out.println("Horario de verão: " + zone);
			System.out.println("Data e Hora: " + date);
		} else {
		  // nao
		}
	}
	
	public static void testeBytes(){
		// put whatever byte array here
        byte[] arr = new byte[] {4, -9, 42, -10, -28, 71, -48, 12, 38, -115, -32, 95, 50, 18, 87, 86, 20, -94, -7, -46};
        
        System.out.println(Base64.encodeBase64String(arr));
	}
	
	public static void downloadNFeNSU(String cnpj, String nsu){
		try{
			//Inicia As Configurações
			//Para Mais informações: https://github.com/Samuel-Oliveira/Java_NFe/wiki/Configura%C3%A7%C3%B5es-Nfe
			ConfiguracoesIniciaisNfe config = iniciaConfigurações();
			
			DistDFeInt distDFeInt = new DistDFeInt();
			distDFeInt.setVersao("1.01");
			distDFeInt.setTpAmb(config.getAmbiente());
			distDFeInt.setCUFAutor(config.getEstado().getCodigoIbge());
			//Substitua os X pelo CNPJ
			distDFeInt.setCNPJ(cnpj);
			
			DistNSU distNSU = new DistNSU();
			ConsChNFe chNFe = new ConsChNFe();
			// Ultilize NSU 0 para pegar todas as Notas, depois troque pelo Ultimo NSU
			distNSU.setUltNSU(nsu);
			distDFeInt.setDistNSU(distNSU);

			//Informe false para não fazer a validação do XML - Ganho De tempo.
			RetDistDFeInt retorno = Nfe.distribuicaoDfe(distDFeInt,true);
			System.out.println("Status:" + retorno.getCStat());
			System.out.println("Motivo:" + retorno.getXMotivo());
			System.out.println("NSU:" + retorno.getUltNSU());
			System.out.println("Max NSU:" + retorno.getMaxNSU());
			List<DocZip> listaDoc =  retorno.getLoteDistDFeInt().getDocZip();
			
			System.out.println("Encontrado "+listaDoc.size() + " Notas.");
			for (DocZip docZip : listaDoc) {
				System.out.println("Schema: "+docZip.getSchema());
				System.out.println("NSU:" + docZip.getNSU());
				System.out.println("XML: "+XmlUtil.gZipToXml(docZip.getValue()));
				
			}
			
			if(Integer.valueOf(retorno.getUltNSU()) < Integer.valueOf(retorno.getMaxNSU())){
				System.out.println("==================================");
				System.out.println("Verificando sequencia de NSU");
				System.out.println("==================================");
				distNSU.setUltNSU(retorno.getUltNSU());
				distDFeInt.setDistNSU(distNSU);
				downloadNFeNSU(cnpj, nsu);
			}else{
				System.out.println("==================================");
				System.out.println("Terminou o processamento");
				System.out.println("NSU:" + retorno.getUltNSU());
				System.out.println("Max NSU:" + retorno.getMaxNSU());
				System.out.println("==================================");
			}
		
		} catch (NfeException | IOException e) {
			System.out.println("Erro:" + e.getMessage());
		}
	}
	
	public static void downloadChave(){
		try{
			//Inicia As Configurações
			//Para Mais informações: https://github.com/Samuel-Oliveira/Java_NFe/wiki/Configura%C3%A7%C3%B5es-Nfe
			ConfiguracoesIniciaisNfe config = iniciaConfigurações();
			
			DistDFeInt distDFeInt = new DistDFeInt();
			distDFeInt.setVersao("1.01");
			distDFeInt.setTpAmb(config.getAmbiente());
			distDFeInt.setCUFAutor(config.getEstado().getCodigoIbge());
			//Substitua os X pelo CNPJ
			distDFeInt.setCNPJ("01554976000250");//01554976000250", "000000000000204"
			
			DistNSU distNSU = new DistNSU();
			ConsChNFe chNFe = new ConsChNFe();

			chNFe.setChNFe("35170952106911000363550015300011131806354308");
			distDFeInt.setConsChNFe(chNFe);
			
			//Informe false para não fazer a validação do XML - Ganho De tempo.
			RetDistDFeInt retorno = Nfe.distribuicaoDfe(distDFeInt,false);
			System.out.println("Status:" + retorno.getCStat());
			System.out.println("Motivo:" + retorno.getXMotivo());

			List<DocZip> listaDoc =  retorno.getLoteDistDFeInt().getDocZip();
			
			for (DocZip docZip : listaDoc) {
				System.out.println("Schema: "+docZip.getSchema());
				System.out.println("NSU:" + docZip.getNSU());
				System.out.println("XML: "+XmlUtil.gZipToXml(docZip.getValue()));
			}
		
		} catch (NfeException | IOException e) {
			System.out.println("Erro:" + e.getMessage());
		}
	}
	
	public static ConfiguracoesIniciaisNfe iniciaConfigurações() throws NfeException {
		// Certificado Windows
		// Certificado certificado = CertificadoUtil.listaCertificadosWindows().get(0);
		
		// Certificado PFX
		Certificado certificado = CertificadoUtil.certificadoPfx("D:\\Projetos MasterSAF DF-e\\Estre Ambiental\\Certificado Digital\\2017\\Certificado Digital Estre Ambiental - 03.147.393.0001-59 - senha cscestre - validade 19.12.2017.pfx", "cscestre");
			
		return ConfiguracoesIniciaisNfe.iniciaConfiguracoes(Estados.SP , ConstantesUtil.AMBIENTE.HOMOLOGACAO,
				certificado, ConstantesUtil.RESOURCE_SCHEMA_NFE, ConstantesUtil.VERSAO.V3_10); //"D:\\Java\\livrariaJava\\Schemas_Java_NFe"
	}
	
}
