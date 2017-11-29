package br.com.prox.controller;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.LocalTime;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.omnifaces.util.Faces;
import org.primefaces.model.DashboardColumn;
import org.primefaces.model.DashboardModel;
import org.primefaces.model.DefaultDashboardColumn;
import org.primefaces.model.DefaultDashboardModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.security.authentication.event.InteractiveAuthenticationSuccessEvent;
import org.springframework.security.core.context.SecurityContextHolder;

import br.com.prox.model.Empresa;
import br.com.prox.repository.ApontamentoDAO;
import br.com.prox.repository.ContratanteDAO;
import br.com.prox.repository.ProjetoDAO;
import br.com.prox.service.ApontamentoService;
import br.com.prox.service.EmpresaService;
import br.com.prox.service.ProjetoService;
import lombok.Data;

@Named
@SessionScoped
@Data
public class PrincipalBean implements ApplicationListener<InteractiveAuthenticationSuccessEvent> {

	
	private String usuarioLogado;
	private Boolean isAutenticado = false;
	
	private Long countApontamentos;
	private Long countProjetos;
	private Long countContratantes;
	
	private Empresa empresaSelecionada;
	
	private DashboardModel model;
	
	@Autowired
	private ApontamentoService apontamentoService;
	
	@Autowired
	private ApontamentoDAO apontamentos;
	
	@Autowired
	private ProjetoService projetoService;
	
	@Autowired
	private ProjetoDAO projetos;
	
	@Autowired
	private ContratanteDAO contratantes;
	
	@Autowired
	private EmpresaService empresaService;
	
	@Autowired
	ThreadPoolTaskScheduler taskScheduler;
	
	@PostConstruct
    public void init() {
        model = new DefaultDashboardModel();
        DashboardColumn column1 = new DefaultDashboardColumn();
        DashboardColumn column2 = new DefaultDashboardColumn();
        DashboardColumn column3 = new DefaultDashboardColumn();
         
        column1.addWidget("clientes");
        column1.addWidget("projetos");
         
        column2.addWidget("apontamentos");
        column2.addWidget("horasPendentes");
         
        column3.addWidget("horasAprovadas");
        column3.addWidget("totalHoras");
 
        model.addColumn(column1);
        model.addColumn(column2);
        model.addColumn(column3);
    }	
	
	@PostConstruct
	public void countApontamentos(){
		countApontamentos = apontamentos.count();
	}
	
	@PostConstruct
	public void countContratantes(){
		countContratantes = contratantes.count();
	}
	
	@PostConstruct
	public void countProjetos(){
		countProjetos = projetos.count();
	}
	
	public void selecionarEmpresa(){
		// Setting a session cookie in current path.
		Faces.addResponseCookie("cnpjSelecionado", empresaSelecionada.getCnpj(), (int) (60 * 60 * 24 * 365 * 10));
		HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
		session.setAttribute("cnpjSelecionado", empresaSelecionada.getCnpj());
		
		/*ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
	    try {
			ec.redirect(((HttpServletRequest) ec.getRequest()).getRequestURI());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
	}
	
	//@PostConstruct
	public void logando(){
		try{
			HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
			session.setAttribute("usuario", SecurityContextHolder.getContext().getAuthentication().getName().toUpperCase());
			
			if(SecurityContextHolder.getContext().getAuthentication() != null){
				System.out.println("" + SecurityContextHolder.getContext().getAuthentication().getPrincipal());
				System.out.println("" + SecurityContextHolder.getContext().getAuthentication().getDetails());
				usuarioLogado = SecurityContextHolder.getContext().getAuthentication().getName().toUpperCase();
				System.out.println("Usuário logado: " + usuarioLogado + " - Acessos: " + SecurityContextHolder.getContext().getAuthentication().getAuthorities());
				setEmpresaSelecionada(empresaService.findByCpnj(Faces.getRequestCookie("cnpjSelecionado")));
				
				Faces.addResponseCookie("cnpjSelecionado", empresaSelecionada.getCnpj(), (int) (60 * 60 * 24 * 365 * 10));
				session.setAttribute("cnpjSelecionado", empresaSelecionada.getCnpj());
			}
		}catch (Exception e) {
			usuarioLogado = "Anônimo";
			System.out.println("Usuário = " + usuarioLogado);
			e.printStackTrace();
			//throw new RuntimeException("Usuário anônimo!");
		}
		
	}
	
	public void logout() throws IOException{
		System.out.println("Autenticado? = " + isAutenticado);
		isAutenticado = true;
		System.out.println("Autenticado? = " + isAutenticado);
		System.out.println("Data e hora = " + LocalDateTime.now());
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, 
                "time out.", "Sessão expirou após 30 minutos de inatividade"));
		System.out.println("redirecionando para login.");
		FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
		HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
		
	}
	
	public void onApplicationEvent(InteractiveAuthenticationSuccessEvent event) {
		 //HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext();
	     //session.setAttribute("usuario", event.getAuthentication().getName());
		 //FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("usuario", "Leonardo");
		
		System.out.println("Logouuuuuuu");
	}
	
	//@PostConstruct
	public void consultaDistribuicoDfe(){
		
		taskScheduler.setPoolSize(10);
		taskScheduler.initialize();
    	taskScheduler.scheduleAtFixedRate(
                  () -> System.out.printf("Task: %s, Time: %s:%n",
                                          Thread.currentThread().getName(),
                                          LocalTime.now()), 1000);
    	taskScheduler.setThreadNamePrefix("Leonardo-");
        
	}

	public void stop(){
		
        System.out.println("shutting down");
        taskScheduler.getScheduledThreadPoolExecutor().shutdownNow();
	}
	
}
