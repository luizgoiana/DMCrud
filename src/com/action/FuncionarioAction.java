package com.action;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;

public class FuncionarioAction {

	@Action(value = "olaMundo", results = {
			@Result(name="ok", location = "/outra.jsp")
	})
	public String execute() {
		System.out.println("Bem vindo ao Struts2");
		return "ok";
	}
	
}
