package br.com.prox.controller;

import java.io.Serializable;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ViewScoped;
import javax.inject.Named;

import org.primefaces.context.RequestContext;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.DualListModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import br.com.prox.model.Apontamento;
import br.com.prox.model.Arquivo;
import br.com.prox.model.Consultor;
import br.com.prox.model.Projeto;
import br.com.prox.repository.ApontamentoDAO;
import br.com.prox.repository.ArquivoDAO;
import br.com.prox.repository.ConsultorDAO;
import br.com.prox.repository.ProjetoDAO;
import br.com.prox.service.ProjetoService;
import br.com.prox.util.FacesMessages;
import lombok.Data;

@Named
@ViewScoped
@Data
//@ManagedBean
@Transactional
public class ProjetoBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Autowired
	private ProjetoService service;
	
	@Autowired
	private ProjetoDAO projetos;
	
	@Autowired
	private ApontamentoDAO apontamentos;
	
	@Autowired
	private ConsultorDAO consultores;
	
	@Autowired
	private ArquivoDAO arquivos;
	
	@Autowired
	private FacesMessages messages;
	
	private Projeto projeto = new Projeto();
	private Projeto projetoSelecionado;
	private List<Projeto> todosProjetos;
	
	private List<Consultor> listaConsultores = new ArrayList();
	
	private DualListModel<Consultor> consultorModel;
	
	private Arquivo arquivo = new Arquivo();
	private List<Arquivo> listaArquivo = new ArrayList();
	
	
	public DualListModel<Consultor> getConsultorModel(){
		// if(this.roleModel==null){ 
			 if(projeto.getConsultor() != null){
				 this.consultorModel = new DualListModel<Consultor>(new ArrayList<>(listaConsultores), new ArrayList<Consultor>(projeto.getConsultor()));
			 }else{
				 this.consultorModel = new DualListModel<Consultor>(new ArrayList<>(listaConsultores), new ArrayList<Consultor>());
			 }
		// }
		 return this.consultorModel;
	}
	
	public void prepararNovoCadastro() {
		projeto = new Projeto();
	}
	
	public void salvar() {
		try {
			projeto.setConsultor(consultorModel.getTarget());
			
			arquivos.save(listaArquivo);
			service.salvar(projeto);
			consultar();
			messages.info("Projeto salvo com sucesso!");
			RequestContext.getCurrentInstance().update(
					Arrays.asList("frm:msgs", "frm:projeto-table"));
		} catch (Exception e) {
			e.printStackTrace();
			messages.error("Erro ao tentar salvar projeto: \n" + e);
			RequestContext.getCurrentInstance().update("frm:msgs");
		}

	}

	public void excluir(){
		try{
			service.excluir(projetoSelecionado);
			projetoSelecionado = null;
			consultar();
			messages.info("Projeto excluído com sucesso!");
		} catch (Exception e) {
			e.printStackTrace();
			messages.error("Erro ao tentar excluir projeto: \n" + e);
			RequestContext.getCurrentInstance().update("frm:msgs");
		}
	}
	
	public void consultar(){
		todosProjetos = projetos.findAll();
	}
	
	public int progresso(Projeto proj){
		try{
//			
			Duration duration = Duration.ZERO;
			Double progresso = 0d;
			
			
			List<Apontamento> apont = apontamentos.totalHoras(proj);
			for(Apontamento ap: apont){
				duration = duration.plusHours(ap.getTempoGasto().getHour()).plusMinutes(ap.getTempoGasto().getMinute());
			}
			
			if(duration.toMinutes() >= 30){
				duration = duration.plusHours(1);
			}
			
			progresso = duration.toHours() / proj.getEstimativa() * 100;
			System.out.println("Progresso..." + progresso);
			return progresso.intValue();
		}catch (Exception e) {
			try {
				messages.error("Erro ao calcular progresso do projeto: \n" + e);
				throw new Exception("Erro ao calcular progresso do projeto");
			} catch (Exception e1) {
				messages.error("Erro ao calcular progresso do projeto: \n" + e1);
				e1.printStackTrace();
				return 0;
			}
		}
	}
	
	@PostConstruct
	public void consultarConsultores(){
		try{
			listaConsultores = consultores.findAll();
		} catch (Exception e) {
			e.printStackTrace();
			messages.error("Erro ao tentar consultar consultores: \n" + e);
			RequestContext.getCurrentInstance().update("frm:msgs");
		}	
	}
	
	public void upload(FileUploadEvent event) {
		System.out.println("Adicionando arquivos a lista...");
		System.out.println("Nome..." + event.getFile().getFileName());
		System.out.println("Tamanho..." + event.getFile().getSize());
		System.out.println("ContentType..." + event.getFile().getContentType());
		System.out.println("Bytes..." + event.getFile().getContents());
		System.out.println("=============================");
		
		arquivo.setNome(event.getFile().getFileName());
		arquivo.setTamanho(event.getFile().getSize());
		arquivo.setTipo(event.getFile().getContentType());
		arquivo.setDados(event.getFile().getContents());
		
		listaArquivo.add(arquivo);
		
		Arrays.asList(arquivo);
		
		projeto.setArquivos(listaArquivo);
		System.out.println(listaArquivo);
    }
	
}
