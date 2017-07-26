package br.com.prox.filtro;

import java.util.Date;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;

import br.com.prox.model.AtividadeApontamento;
import br.com.prox.model.Projeto;
import lombok.Data;

@Data
@Component
public class FiltroApontamento {

	private Projeto projeto;
	private String descricao;
	private AtividadeApontamento atividade;
	
	@NotNull(message = "Date inicial é obrigatória")
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	@Temporal(TemporalType.DATE)
	private Date dataInicial = new Date();
	
	@NotNull(message = "Date final é obrigatória")
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	@Temporal(TemporalType.DATE)
	private Date dataFinal = new Date();
	
}
