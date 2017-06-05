package br.com.prox.model;

public enum AtividadeApontamento {

	SUPORTE("Suporte"),
	DESENVOLVIMENTO("Desenvolvimento"),
	REUNIÃO("Reunião"),
	TESTES("Testes"),
	CONFIGURACAO("Configuração / Parametrização"),
	TREINAMENTO("Treinamento"),
	DOCUMENTAÇAO("Documentação");
	
	private String descricao;
	
	AtividadeApontamento(String descricao) {
		this.descricao = descricao;
	}
	
	public String getDescricao() {
		return descricao;
	}
	
}
