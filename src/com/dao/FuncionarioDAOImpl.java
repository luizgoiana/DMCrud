package com.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.domain.Funcionario;
import com.rowmapper.FuncionarioRowMapper;

@Repository(value="funcionarioDaoRepository")
public class FuncionarioDAOImpl implements FuncionarioDAO {

	@Autowired
	private	JdbcTemplate jdbcTemplate;
	
	@Autowired(required=true)
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	
	@Autowired
	FuncionarioRowMapper funcionarioRowMapper;
	
	@Override
	public void save(Funcionario funcionario) {
		String query = "INSERT INTO tb_funcionarios(nome, idade, sexo) VALUES("
				+ ":nome,"
				+ ":idade,"
				+ ":sexo)";
		
		MapSqlParameterSource parametros = new MapSqlParameterSource();
		parametros.addValue("nome", funcionario.getNome())
			.addValue("idade", funcionario.getIdade())
			.addValue("sexo", funcionario.getSexo() != null ? funcionario.getSexo().getChave() : null);
		
		namedParameterJdbcTemplate.update(query, parametros);
	}

	@Override
	public void update(Funcionario funcionario) {
		String query = "UPDATE tb_funcionarios SET"
				+ " nome = :nome, "
				+ " idade = :idade, "
				+ " sexo = :sexo "
				+ " WHERE id = :id ";
	    
		MapSqlParameterSource parametros = new MapSqlParameterSource();
		parametros.addValue("id", funcionario.getId())
			.addValue("nome", funcionario.getNome())
			.addValue("idade", funcionario.getIdade())
			.addValue("sexo", funcionario.getSexo() != null ? funcionario.getSexo().getChave() : null);
		
		namedParameterJdbcTemplate.update(query, parametros);
	}

	@Override
	public void delete(Funcionario funcionario) {
		String query = "DELETE FROM tb_funcionarios WHERE id = ?";
		getJdbcTemplate().update(query, funcionario.getId());
		
	}

	@Override
	public List<Funcionario> findAll() {
		 String query = "SELECT * FROM tb_funcionarios ORDER BY nome asc";
		 List<Funcionario> funcionarios = getJdbcTemplate().query(query, funcionarioRowMapper);
		 return funcionarios;
	}

	public List<Funcionario> find(Funcionario funcionario) {
		MapSqlParameterSource parametros = new MapSqlParameterSource()
			.addValue("id", funcionario.getId())
			.addValue("nome", "%"+funcionario.getNome()+"%")
			.addValue("idade", funcionario.getIdade())
			.addValue("sexo", funcionario.getSexo() != null ? funcionario.getSexo().getChave() : null);
		
		 String query = "SELECT * FROM tb_funcionarios WHERE 1 = 1";
		 		if (funcionario.getId() != null)
		 			query = query.concat(" AND id = :id ");
		 		
		 		if (funcionario.getNome() != null)
		 			query = query.concat(" AND nome like :nome ");
		 		
		 		if (funcionario.getIdade() != null)
		 			query = query.concat(" AND idade = :idade ");
		 			
		 		if (funcionario.getSexo() != null)
		 			query = query.concat(" AND sexo = :sexo ");
		 
		 List<Funcionario> funcionarios = namedParameterJdbcTemplate.query(query, parametros, funcionarioRowMapper);
		 return funcionarios;
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
