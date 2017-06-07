package br.com.prox.model;

public enum StatusApontamento {

	PENDENTE("Pendente"),
	APROVADO("Aprovado"),
	REPROVADO("Reprovado"),
	RECEBIDO("Recebido");
	
	private String descricao;
	
	StatusApontamento(String descricao) {
		this.descricao = descricao;
	}
	
	public String getDescricao() {
		return descricao;
	}
	
}
