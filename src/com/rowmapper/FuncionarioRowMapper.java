package com.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.domain.Funcionario;

public class FuncionarioRowMapper implements RowMapper<Funcionario>{

	private static final String id = "id";
	private static final String nome = "nome";
	private static final String idade = "idade";
	private static final String sexo = "sexo";
	
	@Override
	public Funcionario mapRow(ResultSet rs, int arg1) throws SQLException {
		Funcionario funcionario = new Funcionario();
		
		funcionario.setId(rs.getInt(id));
		funcionario.setNome(rs.getString(nome));
		funcionario.setIdade(rs.getInt(idade));
		funcionario.setSexo(rs.getInt(sexo));
		
		return funcionario;
	}

}
