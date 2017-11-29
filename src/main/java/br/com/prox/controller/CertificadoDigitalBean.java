package br.com.prox.controller;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.cert.CertificateParsingException;
import java.security.cert.X509Certificate;
import java.sql.Date;
import java.time.LocalTime;
import java.util.Enumeration;
import java.util.List;

import javax.faces.context.FacesContext;
import javax.faces.event.PhaseId;
import javax.inject.Named;

import org.apache.axiom.om.util.Base64;
import org.primefaces.event.FileUploadEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;

import br.com.prox.model.CertificadoDigital;
import br.com.prox.nfe.certificado.Certificado;
import br.com.prox.nfe.certificado.CertificadoService;
import br.com.prox.nfe.certificado.exception.CertificadoException;
import br.com.prox.nfe.exception.NfeException;
import br.com.prox.nfe.util.CertificadoUtil;
import br.com.prox.repository.CertificadoDigitalDAO;
import br.com.prox.service.CertificadoDigitalService;
import br.com.prox.service.EmpresaService;
import br.com.prox.util.FacesMessages;
import lombok.Data;

@Named
//@ViewScoped
@Data
public class CertificadoDigitalBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Autowired
	private CertificadoDigitalService digitalService;
	
	@Autowired
	private EmpresaService empresaService;
	
	@Autowired
	private CertificadoDigitalDAO certificados;
	
	@Autowired
	private FacesMessages messages;
	
	private CertificadoDigital certificado = new CertificadoDigital();
	private CertificadoDigital certificadoSelecionado;
	private List<CertificadoDigital> todosCertificados;
	
	
	public void prepararNovoCadastro() {
		certificado = new CertificadoDigital();
	}
	
	public void salvar() {
		try {
			
//			digitalService.salvar(certificado);
//			consultar();
//			
//			messages.info("Certificado salvo com sucesso!");
//			FacesContext.getCurrentInstance().getExternalContext().redirect("CertificadoDigital.xhtml");
			//RequestContext.getCurrentInstance().update(Arrays.asList("frm:msgs", "frm:item-table"));
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	public String editar(CertificadoDigital certificado){
		
		System.out.println("Chamou editar");
		System.out.println("Descricao: " + certificado.getRaiz());
		this.certificado = certificado;
		
		return "gestaoCertificado.xhtml";
	}

	public void excluir(){
		
		digitalService.excluir(certificadoSelecionado);
		certificadoSelecionado = null;
		consultar();
		
		messages.info("Certificado excluído com sucesso!");
	}
	
	public void consultar(){
		todosCertificados = certificados.findAll();
	}
	
	
	
	public void upload(FileUploadEvent event) {
		System.out.println("Adicionando arquivos...");
		System.out.println("Nome..." + event.getFile().getFileName());
		System.out.println("Tamanho..." + event.getFile().getSize());
		System.out.println("ContentType..." + event.getFile().getContentType());
		System.out.println("Bytes..." + event.getFile().getContents());
		System.out.println("=============================");
		
		if (!PhaseId.INVOKE_APPLICATION.equals(event.getPhaseId())) {
             event.setPhaseId(PhaseId.INVOKE_APPLICATION);
             event.queue();
             System.out.println("IFFFFFFFFFFFFFF");
         } else {
            
     		String senhaDoCertificadoDoCliente = (String) event.getComponent().getAttributes().get("senha");//"Oracle0819";   
     		try{
	     		System.out.println("senhaDoCertificadoDoCliente: " + Base64.encode(senhaDoCertificadoDoCliente.getBytes("UTF-8")));


				digitalService.salvar(event.getFile().getContents(), senhaDoCertificadoDoCliente);
				
				//this.certificado = null;
				consultar();
			
			}catch(UnsupportedEncodingException e){
				messages.error(e.getCause().getLocalizedMessage());
				e.printStackTrace();
			}
        }	
    }
	
	ThreadPoolTaskScheduler s = new ThreadPoolTaskScheduler();
	public void start(){
		
        s.setPoolSize(5);
        s.initialize();
        for (int i = 0; i < 2; i++) {
            s.scheduleAtFixedRate(
                      () -> System.out.printf("Task: %s, Time: %s:%n",
                                              Thread.currentThread().getName(),
                                              LocalTime.now()), 1000);
            s.setThreadNamePrefix("Haine-");
        }
	}
	
	public void stop(){
		
        System.out.println("shutting down");
        s.getScheduledThreadPoolExecutor().shutdownNow();
	}
	
	private static void info(String info) {  
        System.out.println("| INFO: " + info);  
    } 
}
