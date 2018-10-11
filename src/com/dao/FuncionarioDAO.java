package com.dao;

import java.util.List;

import com.domain.Funcionario;

public interface FuncionarioDAO {
	public void salvar(Funcionario funcionario);
	
	public void atualizar(Funcionario funcionario);
	
	public void deletar (Funcionario funcionario);
	
	public List<Funcionario> buscarTodos();
	
	public List<Funcionario> buscar(Funcionario funcionario);
	
	public Funcionario buscarPorId(Integer idFuncionario);
}
