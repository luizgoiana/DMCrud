package com.util;

import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;

public class CustomMapSqlParameterSource extends MapSqlParameterSource {

	@Override
	public CustomMapSqlParameterSource addValue(String paramName, Object value) {
		if (value != null) {
			super.addValue(paramName, value);
		} else {
			super.addValue(paramName, " NULL OR NOT NULL ");
		}
		return this;
	}
	
}
