package com.util;

import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
/**
 * Wrapper para que o método addValue da classe MapSqlParameterSource 
 * simplesmente ignore parametros nulos.
 * @author Luiz
 *
 */
public class CustomMapSqlParameterSource extends MapSqlParameterSource {
 
	@Override
	public MapSqlParameterSource addValue(String paramName, Object value) {
		if (value != null) {
			super.addValue(paramName, value);
		} else {
			super.addValue(paramName, "");
		}
		return this;
	}
}
