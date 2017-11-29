package br.com.prox.nfe.util;

import java.security.cert.CertificateParsingException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bouncycastle.asn1.ASN1ObjectIdentifier;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.DEROctetString;
import org.bouncycastle.asn1.DERPrintableString;
import org.bouncycastle.asn1.DERTaggedObject;
import org.bouncycastle.asn1.DERUTF8String;
import org.bouncycastle.asn1.DLSequence;
import org.bouncycastle.x509.extension.X509ExtensionUtil;

import br.com.prox.nfe.ConfiguracoesIniciaisNfe;
import br.com.prox.nfe.certificado.Certificado;
import br.com.prox.nfe.certificado.CertificadoService;
import br.com.prox.nfe.certificado.exception.CertificadoException;
import br.com.prox.nfe.exception.NfeException;

/**
 * Classe Responsavel Por Carregar as informações do Certificado Digital
 * 
 * @author SaMuK
 * 
 */
public class CertificadoUtil {

	private ConfiguracoesIniciaisNfe configuracoesNfe;
	
	public static final ASN1ObjectIdentifier RESPONSAVEL = new ASN1ObjectIdentifier("2.16.76.1.3.2");   
    public static final ASN1ObjectIdentifier CNPJ = new ASN1ObjectIdentifier("2.16.76.1.3.3");

	// Construtor
	public CertificadoUtil() throws NfeException {
		configuracoesNfe = ConfiguracoesIniciaisNfe.getInstance();
	}

	public void iniciaConfiguracoes() throws NfeException {

		try {

			Certificado certificado = configuracoesNfe.getCertificado();
			CertificadoService.inicializaCertificado(certificado, getClass().getResourceAsStream("/Cacert"));

		} catch (CertificadoException e) {
			throw new NfeException(e.getMessage());
		}

	}
	
	public static List<Certificado> listaCertificadosWindows() throws NfeException {
		try {
			return CertificadoService.listaCertificadosWindows();
		} catch (CertificadoException e) {
			throw new NfeException(e);
		}
	}

	public static Certificado certificadoPfxBytes(byte[] certificadoBytes, String senha) throws NfeException {
		try {
			return CertificadoService.certificadoPfxBytes(certificadoBytes, senha);
		} catch (CertificadoException e) {
			throw new NfeException(e);
		}
	}
	
	public static Certificado certificadoPfx(String caminhoCertificado, String senha) throws NfeException {
		try {
			return CertificadoService.certificadoPfx(caminhoCertificado, senha);
		} catch (CertificadoException e) {
			throw new NfeException(e);
		}
	}
	
	public static Certificado certificadoA3(String marca, String dll, String senha) throws NfeException {
		try {
			return CertificadoService.certificadoA3(marca, dll, senha);
		} catch (CertificadoException e) {
			throw new NfeException(e);
		}
	}
	
	public static Map<String, String> getInfoAdicionais(X509Certificate certificate)  
            throws CertificateParsingException {  
  
       // info("--------------------------------------------------------");
        
        Map<String, String> dados = new HashMap<String, String>();
        
        Collection<?> alternativeNames = X509ExtensionUtil.getSubjectAlternativeNames(certificate);  
        for (Object alternativeName : alternativeNames) {  
            if (alternativeName instanceof ArrayList) {  
                ArrayList<?> listOfValues = (ArrayList<?>) alternativeName;  
                Object value = listOfValues.get(1);
                if(value.toString().contains("@")){
            		dados.put("email", value.toString());
            	}
            	
                if (value instanceof DLSequence) {
                	
                	DLSequence derSequence = (DLSequence) value;  
                	ASN1ObjectIdentifier derObjectIdentifier = (ASN1ObjectIdentifier) derSequence.getObjectAt(0);  
                    DERTaggedObject derTaggedObject = (DERTaggedObject) derSequence.getObjectAt(1);  
                    ASN1Primitive derObject = derTaggedObject.getObject();  
  
                    String valueOfTag = "";  
                    if (derObject instanceof DEROctetString) {  
                        DEROctetString octet = (DEROctetString) derObject;  
                        valueOfTag = new String(octet.getOctets());
                    }   
                    else if (derObject instanceof DERPrintableString) {  
                        DERPrintableString octet = (DERPrintableString) derObject;  
                        valueOfTag = new String(octet.getOctets());
                    }   
                    else if (derObject instanceof DERUTF8String) {  
                        DERUTF8String str = (DERUTF8String) derObject;  
                        valueOfTag = str.getString();
                    }  
                      
                    if ((valueOfTag != null) && (!"".equals(valueOfTag))) {
                        if (derObjectIdentifier.equals(RESPONSAVEL)) {  
                          //  info("Nome do Responsavel: " + derObjectIdentifier + " - " + valueOfTag);
                            dados.put("responsavel", valueOfTag);
                        }  
                        else if (derObjectIdentifier.equals(CNPJ)) {  
                         //   info("CNPJ...............: " + derObjectIdentifier + " - " + valueOfTag);
                            dados.put("cnpj", valueOfTag);
                        }  
                        else {  
                         //   info("OID................: " + derObjectIdentifier + " - " + valueOfTag); 
                        }  
                    }
                }
            }
        }
        return dados;
    }  
	
}