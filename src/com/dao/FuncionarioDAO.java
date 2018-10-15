package com.dao;

import java.util.List;

import com.domain.Funcionario;

public interface FuncionarioDAO {
	public void save(Funcionario funcionario);
	
	public void update(Funcionario funcionario);
	
	public void delete (Funcionario funcionario);
	
	public List<Funcionario> findAll();
	
	public List<Funcionario> find(Funcionario funcionario);
}
