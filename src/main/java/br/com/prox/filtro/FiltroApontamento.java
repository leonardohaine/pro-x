package br.com.prox.filtro;

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
	
}
