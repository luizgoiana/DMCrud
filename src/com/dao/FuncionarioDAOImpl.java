package com.dao;

import java.util.List;

import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import com.domain.Funcionario;
import com.rowmapper.FuncionarioRowMapper;
import com.util.CustomMapSqlParameterSource;

@Component(value="funcionarioDaoComponent")
public class FuncionarioDAOImpl implements FuncionarioDAO {

	@Autowired
	private	JdbcTemplate jdbcTemplate;
	
	@Autowired(required=true)
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	
	@Override
	public void salvar(Funcionario funcionario) {
		String query = "INSERT INTO tb_funcionarios(nome, idade, sexo) VALUES("
				+ ":nome,"
				+ ":idade,"
				+ ":sexo)";
		
		MapSqlParameterSource parametros = new MapSqlParameterSource();
		parametros.addValue("nome", funcionario.getNome());
		parametros.addValue("idade", funcionario.getIdade());
		parametros.addValue("sexo", funcionario.getSexo());
		
		namedParameterJdbcTemplate.update(query, parametros);
	}

	@Override
	public void atualizar(Funcionario funcionario) {
		String query = "UPDATE tb_funcionarios SET"
				+ " nome = :nome, "
				+ " idade = :idade, "
				+ " sexo = :sexo "
				+ " WHERE id = :id ";
	    
		MapSqlParameterSource parametros = new MapSqlParameterSource();
		parametros.addValue("id", funcionario.getId());
		parametros.addValue("nome", funcionario.getNome());
		parametros.addValue("idade", funcionario.getIdade());
		parametros.addValue("sexo", funcionario.getSexo());
		
		namedParameterJdbcTemplate.update(query, parametros);
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
		MapSqlParameterSource parametros = new MapSqlParameterSource()
			.addValue("id", funcionario.getId())
			.addValue("nome", funcionario.getNome());
		
		 String query = "SELECT * FROM tb_funcionarios WHERE 1 = 1";
		 		if (funcionario.getId() != null)
		 			query.concat("AND id = :id ");
		 		
		 		if (funcionario.getNome() != null)
			 		query.concat("AND nome = :nome ");
		 		
		 		if (funcionario.getIdade() != null)
		 			query.concat("AND idade = :idade ");
		 			
		 		if (funcionario.getSexo() != null)
		 			query.concat("AND sexo = :sexo ");
		 
		 List<Funcionario> funcionarios = namedParameterJdbcTemplate.query(query, parametros, new FuncionarioRowMapper());
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

	public NamedParameterJdbcTemplate getNamedParameterJdbcTemplate() {
		return namedParameterJdbcTemplate;
	}

	public void setNamedParameterJdbcTemplate(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
		this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
	}
	
	
	
}
