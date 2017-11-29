/**
 * 
 */
package br.com.prox.nfe;

import br.com.prox.nfe.exception.NfeException;
import br.inf.portalfiscal.nfe.conscad.TConsCad;
import br.inf.portalfiscal.nfe.conscad.TRetConsCad;
import br.inf.portalfiscal.nfe.consreci.TConsReciNFe;
import br.inf.portalfiscal.nfe.consreci.TRetConsReciNFe;
import br.inf.portalfiscal.nfe.conssit.TConsSitNFe;
import br.inf.portalfiscal.nfe.conssit.TRetConsSitNFe;
import br.inf.portalfiscal.nfe.consstatserv.TConsStatServ;
import br.inf.portalfiscal.nfe.distDFeInt_v1.DistDFeInt;
import br.inf.portalfiscal.nfe.envEventoCancNFe_v1.TEnvEvento;
import br.inf.portalfiscal.nfe.envi.TEnviNFe;
import br.inf.portalfiscal.nfe.envi.TRetEnviNFe;
import br.inf.portalfiscal.nfe.eventoCancNFe_v1.TRetEnvEvento;
import br.inf.portalfiscal.nfe.inut.TInutNFe;
import br.inf.portalfiscal.nfe.inut.TRetInutNFe;
import br.inf.portalfiscal.nfe.retDistDFeInt_v1.RetDistDFeInt;
import br.inf.portalfiscal.nfe.retconsstatserv.TRetConsStatServ;

/**
 * @author Samuel Oliveira - samuk.exe@hotmail.com - www.samuelweb.com.br
 *
 */
public class Nfe {
	
	/**
	 * Construtor privado
	 */
	private Nfe() {
	}

	/**
	 * Metodo Responsavel Buscar o Status de Serviço do Servidor da Sefaz
	 * No tipo Informar ConstantesUtil.NFE ou ConstantesUtil.NFCE
	 * 
	 * @param consStatServ
	 * @param valida
	 * @param tipo
	 * @return
	 * @throws NfeException
	 */
	public static TRetConsStatServ statusServico(TConsStatServ consStatServ, boolean valida , String tipo) throws NfeException{
		
		return Status.statusServico(consStatServ , valida, tipo);
		
	}
	
	/**
	 * Classe Reponsavel Por Consultar o status da NFE na SEFAZ
	 * No tipo Informar ConstantesUtil.NFE ou ConstantesUtil.NFCE
	 * 
	 * @param consSitNFe
	 * @return TRetConsSitNFe
	 * @throws NfeException 
	 */
	public static TRetConsSitNFe consultaXml(TConsSitNFe consSitNFe, boolean valida, String tipo) throws NfeException{
		
		return ConsultaXml.consultaXml(consSitNFe , valida, tipo);
			
	}
	
	/**
	 * Classe Reponsavel Por Consultar o cadastro do Cnpj na SEFAZ
	 * 
	 * @param consCad
	 * @return TRetConsCad
	 * @throws NfeException 
	 */
	public static TRetConsCad consultaCadastro(TConsCad consCad, boolean valida) throws NfeException{
		
		return ConsultaCadastro.consultaCadastro(consCad, valida);
		
	}
	
	/**
	 * Classe Reponsavel Por Consultar o retorno da NFE na SEFAZ
	 * No tipo Informar ConstantesUtil.NFE ou ConstantesUtil.NFCE
	 * 
	 * @param consReciNFe
	 * @param valida
	 * @param tipo
	 * @return
	 * @throws NfeException
	 */
	public static TRetConsReciNFe consultaXml(TConsReciNFe consReciNFe, boolean valida, String tipo) throws NfeException{
		
		return ConsultaRecibo.reciboNfe(consReciNFe , valida, tipo);
		
	}
	
	/**
	 * Classe Reponsavel Por Inutilizar a NFE na SEFAZ
	 * No tipo Informar ConstantesUtil.NFE ou ConstantesUtil.NFCE
	 * 
	 * @param inutNFe
	 * @param valida
	 * @param tipo
	 * @return
	 * @throws NfeException
	 */
	public static TRetInutNFe inutilizacao(TInutNFe inutNFe, boolean valida, String tipo) throws NfeException{
		
		return Inutilizar.inutiliza(inutNFe , valida, tipo);
		
	}
	
	/**
	 * Classe Reponsavel Por Consultar a Distribuiçao da NFE na SEFAZ
	 * 
	 * @param distDFeInt
	 * @param valida
	 * @return
	 * @throws NfeException
	 */
	public static RetDistDFeInt distribuicaoDfe(DistDFeInt distDFeInt, boolean valida) throws NfeException{
		
		return DistribuicaoDFe.consultaNfe(distDFeInt , valida);
		
	}
	
	/**
	 * Metodo para Montar a NFE.
	 * 
	 * @param enviNFe
	 * @param valida
	 * @return
	 * @throws NfeException
	 */
	public static TEnviNFe montaNfe(TEnviNFe enviNFe, boolean valida) throws NfeException{
		
		return Enviar.montaNfe(enviNFe , valida);
				
	}
	
	/**
	 * Metodo para Enviar a NFE.
	 * No tipo Informar ConstantesUtil.NFE ou ConstantesUtil.NFCE
	 * 
	 * @param enviNFe
	 * @param tipo
	 * @return
	 * @throws NfeException
	 */
	public static TRetEnviNFe enviarNfe(TEnviNFe enviNFe, String tipo) throws NfeException{
		
		return Enviar.enviaNfe(enviNFe, tipo);
		
	}
	
	/**
	 * 	 * Metodo para Cancelar a NFE.
	 * No tipo Informar ConstantesUtil.NFE ou ConstantesUtil.NFCE
	 * 
	 * @param evento
	 * @param valida
	 * @param tipo
	 * @return
	 * @throws NfeException
	 */

	public static TRetEnvEvento cancelarNfe(TEnvEvento evento, boolean valida, String tipo) throws NfeException{
		
		return Evento.eventoCancelamento(evento , valida, tipo);
		
	}
	
	/**
	 * 	 * Metodo para Envio da Carta De Correção da NFE.
	 * No tipo Informar ConstantesUtil.NFE ou ConstantesUtil.NFCE
	 * 
	 * @param evento
	 * @param valida
	 * @param tipo
	 * @return
	 * @throws NfeException
	 */
	public static br.inf.portalfiscal.nfe.envCCe_v1.TRetEnvEvento cce(br.inf.portalfiscal.nfe.envCCe_v1.TEnvEvento evento, boolean valida, String tipo) throws NfeException{
		
		return Evento.eventoCce(evento , valida,tipo);
		
	}

	/**
	 * Metodo para Manifestação da NFE.
	 * 
	 * @param envEvento
	 * @param valida
	 * @return
	 * @throws NfeException
	 */
	/*public static br.inf.portalfiscal.nfe.retEnvConfRecebto.TRetEnvEvento manifestacao(br.inf.portalfiscal.nfe.envConfRecebto.TEnvEvento envEvento, boolean valida) throws NfeException {
		
		return Evento.eventoManifestacao(envEvento , valida);
		
	}*/
	
	
}
