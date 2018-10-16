package com.converter;

import java.util.Map;

import org.apache.struts2.util.StrutsTypeConverter;

import com.domain.SexoEnum;

public class SexoConverter extends StrutsTypeConverter {

	@Override
	public Object convertFromString(Map context, String[] values, Class clazz) {
		SexoEnum sexoEnum = SexoEnum.getFromValue(Integer.parseInt(values[0]));
		return sexoEnum;
	}

	@Override
	public String convertToString(Map context, Object value) {
		SexoEnum sexoEnum = (SexoEnum) value;
		return sexoEnum == null ? null : sexoEnum.getDescricao();
	}

}
