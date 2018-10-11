package com.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Component;

import com.domain.Funcionario;
import com.rowmapper.FuncionarioRowMapper;
import com.util.CustomMapSqlParameterSource;

@Component(value="funcionarioDaoComponent")
public class FuncionarioDAOImpl implements FuncionarioDAO {

	@Autowired
	private	JdbcTemplate jdbcTemplate;
	
	@Override
	public void salvar(Funcionario funcionario) {
		String query = "INSERT INTO tb_funcionarios(nome, idade, sexo) VALUES("
				+ ":nome,"
				+ ":idade,"
				+ ":sexo)";
		
		CustomMapSqlParameterSource parametros = new CustomMapSqlParameterSource();
		parametros.addValue("nome", funcionario.getNome());
		parametros.addValue("idade", funcionario.getIdade());
		parametros.addValue("sexo", funcionario.getSexo());
		
		jdbcTemplate.update(query, parametros);
	}

	@Override
	public void atualizar(Funcionario funcionario) {
		String query = "UPDATE tb_funcionarios SET"
				+ "nome = :nome"
				+ "idade = :idade"
				+ "sexo= :sexo"
				+ "WHERE id= :id";
	    
		CustomMapSqlParameterSource parametros = new CustomMapSqlParameterSource();
		parametros.addValue("id", funcionario.getId());
		parametros.addValue("nome", funcionario.getNome());
		parametros.addValue("idade", funcionario.getIdade());
		parametros.addValue("sexo", funcionario.getSexo());
		
		getJdbcTemplate().update(query, parametros);
	}

	@Override
	public void deletar(Funcionario funcionario) {
		String query = "DELETE FROM tb_funcionarios WHERE id = ?";
		getJdbcTemplate().update(query, funcionario.getId());
		
	}

	@Override
	public List<Funcionario> buscarTodos() {
		 String query = "SELECT * FROM tb_funcionarios";
		 List<Funcionario> funcionarios = getJdbcTemplate().query(query, new FuncionarioRowMapper());
		 return funcionarios;
	}

	public List<Funcionario> buscar(Funcionario funcionario) {
		CustomMapSqlParameterSource parametros = new CustomMapSqlParameterSource();
		parametros.addValue("id", funcionario.getId());
		parametros.addValue("nome", funcionario.getNome());
		parametros.addValue("idade", funcionario.getIdade());
		parametros.addValue("sexo", funcionario.getSexo());
		
		 String query = "SELECT * FROM tb_funcionarios WHERE ("
		 		+ "id = :id "
		 		+ "AND nome = :nome "
		 		+ "AND idade = :idade"
		 		+ "AND sexo = :sexo)";
		 
		 List<Funcionario> funcionarios = getJdbcTemplate().query(query, new FuncionarioRowMapper(), parametros);
		 return funcionarios;
	}

	@Override
	public Funcionario buscarPorId(Integer idFuncionario) {
		Funcionario funcionario = new Funcionario();
		funcionario.setId(idFuncionario);
		return buscar(funcionario).get(0);
	}

	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
	
	
}
