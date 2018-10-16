package com.domain;

import com.opensymphony.xwork2.conversion.annotations.TypeConversion;

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
	
	@TypeConversion(converterClass=com.converter.SexoConverter.class)
	public SexoEnum getSexo() {
		return sexo;
	}
	
	@TypeConversion(converterClass=com.converter.SexoConverter.class)
	public void setSexo(SexoEnum sexo) {
		this.sexo = sexo;
	}
	
}
