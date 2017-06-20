package br.com.prox.controller;

import java.io.Serializable;
import java.time.Duration;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;

import javax.faces.bean.ViewScoped;
import javax.inject.Named;

import org.apache.commons.lang3.time.DurationFormatUtils;
import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.InvalidDataAccessApiUsageException;

import br.com.prox.filtro.FiltroApontamento;
import br.com.prox.model.Apontamento;
import br.com.prox.model.AtividadeApontamento;
import br.com.prox.model.StatusApontamento;
import br.com.prox.repository.ApontamentoDAO;
import br.com.prox.service.ApontamentoService;
import br.com.prox.util.FacesMessages;
import lombok.Data;

@Named
@ViewScoped
@Data
public class ApontamentoBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Autowired
	private ApontamentoService service;
	
	@Autowired
	private ApontamentoDAO apontamentos;
	
	@Autowired
	private FacesMessages messages;
	
	@Autowired
	private FiltroApontamento filtro;
	
	private Apontamento apontamento = new Apontamento();
	private Apontamento apontamentoSelecionado;
	private List<Apontamento> todosApontamentos;
	
	public void prepararNovoCadastro() {
		apontamento = new Apontamento();
	}
	
	public void salvar() {
		try {
			//apontamento.setTempoGasto(new Time(apontamento.getTempoGasto()));
			
			service.salvar(apontamento);
			consultar();
			
			messages.info("Apontamento realizdo com sucesso!");
			
			RequestContext.getCurrentInstance().update(
					Arrays.asList("frm:msgs", "frm:apontamento-table"));
		} catch (Exception e) {
			e.printStackTrace();
			messages.error("Erro ao tentar salvar Apontamento!");
			RequestContext.getCurrentInstance().update(
					Arrays.asList("frm:msgs", "frm:apontamento-table"));
		}

	}

	public void excluir(){
		try{
			service.excluir(apontamentoSelecionado);
			apontamentoSelecionado = null;
			
			consultar();
			
			messages.info("Apontamento excluído com sucesso!");
		} catch (Exception e) {
			e.printStackTrace();
			messages.error("Erro ao tentar excluir apontamento: \n" + e);
			RequestContext.getCurrentInstance().update("frm:msgs");
		}	
	}
	
	public List<StatusApontamento> todosStatusApontamento() {
		return Arrays.asList(StatusApontamento.values());
	}
	
	public List<AtividadeApontamento> todasAtividadesApontamento() {
		return Arrays.asList(AtividadeApontamento.values());
	}
	
	public void aprovar() {
		
		apontamento.setStatus(StatusApontamento.APROVADO);
		service.salvar(apontamento);
		consultar();
		
		messages.info("Apontamento aprovado com sucesso!");
		RequestContext.getCurrentInstance().update(
				Arrays.asList("frm:msgs", "frm:apontamento-table"));
	}
	
	public void filtro(){
		try{
			todosApontamentos = service.filtroApontamento(filtro);
		}catch (InvalidDataAccessApiUsageException e) {
			if(filtro.getDataInicial() == null || filtro.getDataFinal() == null){
				messages.error("Preencha a data inicial e data final");
				return;
			}
			
			e.printStackTrace();
			messages.error("Erro ao selecionar filtro: " + e);
			RequestContext.getCurrentInstance().update(
					Arrays.asList("frm:msgs", "frm:apontamento-table"));
		}	
	}
	
	public String getSomaProjetos(){
		
		Duration du = Duration.ZERO;
		
		for(Apontamento ap : todosApontamentos){
			du = du.plusHours(ap.getTempoGasto().getHour()).plusMinutes(ap.getTempoGasto().getMinute());
		}
		System.out.println("Calculando...");
		return DurationFormatUtils.formatDuration(du.toMillis(), "HH:mm");
	} 
	
	public void consultar(){
		try{
			todosApontamentos = apontamentos.findAll();
			filtro = new FiltroApontamento();
		} catch (Exception e) {
			e.printStackTrace();
			messages.error("Erro ao tentar consultar apontamento: \n" + e);
			RequestContext.getCurrentInstance().update("frm:msgs");
		}	
	}
	
	public void onRowSelect(SelectEvent event) {
		
		if(apontamentoSelecionado != null){
			System.out.println("Get total horas...");
			List<Apontamento> t = apontamentos.totalHoras(apontamentoSelecionado.getProjeto());
			System.out.println("Get total horas..." + t);
		}
		
//        FacesMessage msg = new FacesMessage("Apontamento com projeto: ", ((Apontamento) event.getObject()).getId().toString());
//        FacesContext.getCurrentInstance().addMessage(null, msg);
//        
//        messages.info(apontamentoSelecionado.getProjeto().getDescricao());
//        messages.info(apontamento != null ? apontamento.getDescricao() : null);
    }
	
}
