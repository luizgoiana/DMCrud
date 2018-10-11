package com.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

import com.dao.FuncionarioDAO;
import com.dao.FuncionarioDAOImpl;
import com.domain.Funcionario;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class FuncionarioAction extends ActionSupport implements ModelDriven<Funcionario> {

	private static final long serialVersionUID = -734660070036311886L;
	
	private Funcionario funcionario = new Funcionario();
	private List<Funcionario> funcionarios = new ArrayList<Funcionario>();
	
	@Autowired(required=true)
	private FuncionarioDAO funcionarioDaoComponent;

	@Override
	public Funcionario getModel() {
		return funcionario;
	}
	
//	@Action(value = "salvarOuAtualizarFuncionario", results = {
//			@Result(name="success ", location = "/funcionarios.jsp"),
//			@Result(name="input ", location = "/funcionarios.jsp")
//	})
	public String salvarOuAtualizar()
	{	
		if (funcionario.getId() != null) {
			funcionarioDaoComponent.atualizar(funcionario);
		} else {
			funcionarioDaoComponent.salvar(funcionario);
		}
		return SUCCESS;
	}
	
	public String listar()
	{
		funcionarios = funcionarioDaoComponent.buscarTodos();
		return SUCCESS;
	}
	
	public String deletar()
	{
		HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get( ServletActionContext.HTTP_REQUEST);
		Funcionario funcionario = new Funcionario();
		funcionario.setId(Integer.parseInt( request.getParameter("id")));
		funcionarioDaoComponent.deletar(funcionario);
		return SUCCESS;
	}
	
	public String editar()
	{
		HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get( ServletActionContext.HTTP_REQUEST);
		funcionario = funcionarioDaoComponent.buscarPorId(Integer.parseInt( request.getParameter("id")));
		return SUCCESS;
	}
	
	
//	@Action(value = "olaMundo", results = {
//			@Result(name="ok", location = "/outra.jsp")
//	})
//	public String execute() {
//		System.out.println("Bem vindo ao Struts2");
//		return "ok";
//	}
	

	
	
	public Funcionario getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}

	public List<Funcionario> getFuncionarios() {
		return funcionarios;
	}

	public void setFuncionarios(List<Funcionario> funcionarios) {
		this.funcionarios = funcionarios;
	}
	
}
