package com.domain;

public class Funcionario extends AbstractEntity {

	private String nome;
	private Integer idade;
	private SexoEnum sexo;
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public Integer getIdade() {
		return idade;
	}
	public void setIdade(Integer idade) {
		this.idade = idade;
	}
	public SexoEnum getSexo() {
		return sexo;
	}
	public void setSexo(SexoEnum sexo) {
		this.sexo = sexo;
	}
	public void setSexo(Object sexo) {
		this.sexo = SexoEnum.getFromValue(Integer.parseInt(((String[])sexo)[0]));
	}
	
}
