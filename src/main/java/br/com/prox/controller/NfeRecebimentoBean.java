package br.com.prox.controller;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.omnifaces.util.Messages;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.springframework.beans.factory.annotation.Autowired;

import br.com.prox.model.NfeRecebimento;
import br.com.prox.nfe.danfe.DanfeNfePathJasper;
import br.com.prox.nfe.danfe.ParametrosEmpresaDanfe;
import br.com.prox.repository.DocumentoXmlDAO;
import br.com.prox.repository.NfeRecebimentoDAO;
import br.com.prox.service.NfeRecebimentoService;
import br.com.prox.util.FacesMessages;
import lombok.Data;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRXmlDataSource;
import net.sf.jasperreports.engine.util.JRLoader;

//@ManagedBean
@Named
@ViewScoped
@Data
public class NfeRecebimentoBean implements Serializable {

	private static final long serialVersionUID = 1L;
	

	private NfeRecebimento nfeRecebimento = new NfeRecebimento();

	private NfeRecebimento nfeRecebimentoSelecionado;
	
	private StreamedContent file;
	
	@Autowired
	private NfeRecebimentoDAO nfeRecebimentoDao;
	
	@Autowired
	private DocumentoXmlDAO downloadXmlDao;
	
	@Autowired
	private NfeRecebimentoService service;
	
	private List<NfeRecebimento> todosNfeRecebimento;
	
	@Autowired
	private FacesMessages messages;

	public void prepararNovoCadastro() {
		nfeRecebimento = new NfeRecebimento();
	}
	
	@PostConstruct
	public void consultar() {
		if( FacesContext.getCurrentInstance() != null){
			HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
			String cnpjSelecionado = (String) session.getAttribute("cnpjSelecionado");
			todosNfeRecebimento = nfeRecebimentoDao.findByCnpjEmpresa(cnpjSelecionado.replaceAll("[^0-9]", ""));
		}
	}

	
	public void downloadXmlRecebimento(){
		System.out.println("Download XML de Recebimento de NF-e: " + nfeRecebimento.getNfe_doc_xml_emissao_id());
		InputStream stream = new ByteArrayInputStream(downloadXmlDao.findOne(nfeRecebimento.getNfe_doc_xml_emissao_id()).getXml());
				//FacesContext.getCurrentInstance().getExternalContext().getResourceAsStream("/resources/demo/images/optimus.jpg");
        file = new DefaultStreamedContent(stream, "application/xml", nfeRecebimento.getChave() + ".xml");
        messages.info("Chave de acesso: " + nfeRecebimento.getChave());
	}
	
	public void abrirDanfe(){
		
		// NfePrintRequestMapper mapper = new NfePrintRequestMapper(nfXmlWrapper);
	    // jasperPrint = createJasperPrintDanfe(mapper.mapParaPortal(paramsImpressao, parametrosEmpresaDanfe), paramsImpressao.conteudoXml, parametrosEmpresaDanfe.getDiretorioModeloDanfe(), paramsImpressao.formatoImpressao.intValue(), aplicacaoDfe);
		
		String xml = new String(downloadXmlDao.findOne(nfeRecebimento.getNfe_doc_xml_emissao_id()).getXml());
		
		ParametrosEmpresaDanfe param = new ParametrosEmpresaDanfe("leonardo.haine@gmail.com", "leonardo.haine@gmail.com", 4);
		param.setPathDanfeDir("src/main/resources/modelo/danfe/");
		param.setDiretorioModeloDanfe("src/main/resources/modelo/danfe/");
		
		Map<String, Object> parametros =  new HashMap();
		parametros.put("REPORT_LOCALE", new Locale("pt", "BR"));
		parametros.put("cnpjEmissor", "42123091004513");
	    parametros.put("inscricaoEmissor", "669615659110");
	    parametros.put("QTD_ITENS", String.valueOf(1));
	    parametros.put("DM_RECEBIMENTO", "true");
	    parametros.put("nrItens", 1);
	    parametros.put("NR_VRS_XML", "3.10");
	    parametros.put("INFO_CONTRIB", "TESTE INFCPL TABOAO DA SERRA");
	    //parametros.put("CHAVE_ACESSO", "35171042123091004513550010000164541013576035");
	    parametros.put("NR_PROT_ENV", "135170004186151");
	    parametros.put("VALOR_APROX_TRIBUTOS", "");
	    parametros.put("qtdPaginas", Integer.valueOf("1"));
	    
	    InputStream in = getClass().getResourceAsStream("/modelo/danfe/logo_recebimento.png");
	    parametros.put("LOGO", in);
	    parametros.put("DM_RECEBIMENTO", "true");
	    
		try {
			JasperPrint jasperPrint = createJasperPrintDanfe(parametros, xml, param.getPathDanfeDir(), 1, "nfe");
			//file = new DefaultStreamedContent(new ByteArrayInputStream(xml.getBytes("UTF-8")), "application/xml", nfeRecebimento.getChave() + ".pdf");
		} catch (JRException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private JasperPrint createJasperPrintDanfe(Map<String, Object> parametros, String xml, String pathDanfeDir,
			int formatoImpressao, String aplicacaoDfe) throws JRException {

		JasperReport capa = null;
		DanfeNfePathJasper danfeNfePathJasper = new DanfeNfePathJasper();
		if ((pathDanfeDir != null) && (pathDanfeDir.trim().length() > 0)) {
			// pathDanfeDir = DanfeCustomizado.formadaDiretorioDoctoCustomizado(pathDanfeDir);
			danfeNfePathJasper.setBasePathDanfe(pathDanfeDir);
		}
		capa = (JasperReport) JRLoader.loadObject(danfeNfePathJasper.getCapaDanfe(Integer.valueOf(formatoImpressao)));
		parametros.put("SUBREPORT_DIR", danfeNfePathJasper.getItemDanfe(Integer.valueOf(formatoImpressao)));
		parametros.put("SUBREPORT_DUPLICATAS", danfeNfePathJasper.getDuplicatasDanfe(Integer.valueOf(formatoImpressao)));
		parametros.put("SUBREPORT_OBS_DIR", danfeNfePathJasper.getOBSDanfe(Integer.valueOf(formatoImpressao)));

		parametros.put("XMLPATHROOT", "/nfeProc/NFe");// verificaSeNfeProc(xml)  ? "/nfeProc/NFe" : "/NFe");

		byte[] xmlByte = null;
		try {
			xmlByte = xml.getBytes("UTF-8");
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException("Bug", e);
		}
		ByteArrayInputStream input = new ByteArrayInputStream(xmlByte);
		JRXmlDataSource jrxmlds = new JRXmlDataSource(input, verificaSeNfeProc(xml) ? "/nfeProc/NFe" : "/NFe");

		JasperPrint jasperPrint = JasperFillManager.fillReport(capa, parametros, jrxmlds);
		
		byte[] b = JasperExportManager.exportReportToPdf(jasperPrint); 
        HttpServletResponse res = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        res.setContentType("application/pdf");
        //Código abaixo gerar o relatório e disponibiliza diretamente na página 
        res.setHeader("Content-disposition", "inline;filename=arquivo.pdf");
        //Código abaixo gerar o relatório e disponibiliza para o cliente baixar ou salvar 
        //res.setHeader("Content-disposition", "attachment;filename=arquivo.pdf");
        try {
			res.getOutputStream().write(b);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        res.getCharacterEncoding();
        FacesContext.getCurrentInstance().responseComplete();
        System.out.println("saiu do visualizar relatorio");
		
		return jasperPrint;
	}
	
	
	
	public boolean verificaSeNfeProc(String xml) {
		/*Map<Integer, String> xmlByXPath = this.xmlXPathUtil.getXmlByXPath(xml, "//*[name()='nfeProc']");
		if (xmlByXPath.isEmpty()) {
			return false;
		}*/
		return true;
	}
	
	
	public StreamedContent getFile() {
        return file;
    }
	
	public void excluir() {
		try {
			service.excluir(nfeRecebimentoSelecionado);
			System.out.println("NFe Recebimento " + nfeRecebimentoSelecionado.getChave() + " excluido com sucesso");
			nfeRecebimentoSelecionado = null;
			consultar();
		} catch (Exception e) {
			Messages.addGlobalError("Ocorreu um erro ao exluir o contratante");
			e.printStackTrace();

		}

	}
	
	
}
