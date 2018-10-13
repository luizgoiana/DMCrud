package com.domain;

public enum SexoEnum {
	
	MASCULINO(0, "MASCULINO"), FEMININO(1, "FEMININO");
	
	private Integer chave;
	private String descricao;
	
	
	
	private SexoEnum(Integer chave, String descricao) {
		this.chave = chave;
		this.descricao = descricao;
	}
	
	public static SexoEnum getFromValue(Integer chave) {
		if (chave == 0) {
			return MASCULINO;
		} else if (chave == 1) {
			return FEMININO;
		}
		throw new RuntimeException("Valor desconhecido: ".concat(chave.toString()));
	}
	
	
	public Integer getChave() {
		return chave;
	}
	public void setChave(Integer chave) {
		this.chave = chave;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	
}
