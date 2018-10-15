package com.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.interceptor.validation.SkipValidation;
import org.springframework.beans.factory.annotation.Autowired;

import com.dao.FuncionarioDAO;
import com.dao.FuncionarioDAOImpl;
import com.domain.Funcionario;
import com.domain.SexoEnum;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.service.FuncionarioService;

public class FuncionarioAction extends ActionSupport implements ModelDriven<Funcionario> {

	private static final long serialVersionUID = -734660070036311886L;
	
	private Funcionario funcionario = new Funcionario();
	private List<Funcionario> funcionarios = new ArrayList<Funcionario>();
	
	private SexoEnum[] sexoArray = SexoEnum.values();
	
	@Autowired()
	private FuncionarioService funcionarioServiceComponent;
	
	

	@Override
	public Funcionario getModel() {
		return funcionario;
	}
	
	public String saveOrUpdate()
	{	
		funcionarioServiceComponent.saveOrUpdate(funcionario);
		return SUCCESS;
	}
	
	@SkipValidation
	public String find()
	{
		funcionarios = funcionarioServiceComponent.find(funcionario);
		return SUCCESS;
	}
	
	@SkipValidation
	public String list()
	{
		funcionarios = funcionarioServiceComponent.findAll();
		return SUCCESS;
	}
	
	@SkipValidation
	public String delete()
	{
		HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get( ServletActionContext.HTTP_REQUEST);
		funcionarioServiceComponent.deleteById(Integer.parseInt(request.getParameter("id")));
		return SUCCESS;
	}
	
	@SkipValidation
	public String edit()
	{
		HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get( ServletActionContext.HTTP_REQUEST);
		funcionario = funcionarioServiceComponent.findById(Integer.parseInt( request.getParameter("id")));
		return SUCCESS;
	}
	
	public void validate(){
	    if (funcionario.getNome().length() == 0) {
	        addFieldError("funcionario.nome", "O nome é obrigatório.");
	    }
	    
	    if (funcionario.getNome().length() >= 40) {
	        addFieldError("funcionario.nome", "Informe um nome com até 40 caracteres");
	    }
	}
	
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
	
	public SexoEnum[] getSexoArray() {
		return sexoArray;
	}

	public void setSexoArray(SexoEnum[] sexoArray) {
		this.sexoArray = sexoArray;
	}
	
}
