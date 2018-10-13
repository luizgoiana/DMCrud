<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib uri="/struts-tags" prefix="s"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Registration Page</title>
<s:head />
<style type="text/css">
@import url(style.css);
</style>
</head>
<body>
<s:form method="post" id="funcionarioForm" action="salvarOuAtualizarFuncionario">
		<s:textfield name="funcionario.id" readonly="true"/>
		<s:textfield name="funcionario.nome" label="Nome do Funcionario" />
		<s:textfield name="funcionario.idade" label="Idade do Funcionario" />
		<s:select name="sexo" list="sexoArray" listValue="descricao" listKey="chave" 
        	headerKey="" headerValue="Select" 
            label="Informe o sexo" />
		
		<s:submit value="Gravar" onclick="formHandle(1)"/>
		<s:submit value="Pesquisar" onclick="formHandle(2)" />
		<s:reset type="button" value="Cancelar"/>
</s:form>

<script>
 function formHandle(op) {
		if (op == 1) {
			document.getElementById("funcionarioForm").setAttribute("action", "/CrudStruts/salvarOuAtualizarFuncionario");
		} else {
			document.getElementById("funcionarioForm").setAttribute("action", "/CrudStruts/buscarFuncionario");
		}
	}
</script>

<s:if test="funcionarios.size() > 0">
	<div class="content">
	<table class="userTable" cellpadding="5px">
		<tr class="even">
			<th>Código</th>
			<th>Funcionário</th>
			<th>Idade</th>
			<th>Sexo</th>
			<th>Editar</th>
			<th>Apagar</th>
		</tr>
		<s:iterator value="funcionarios" status="funcionarioStatus">
			<tr
				class="<s:if test="funcionarioStatus == true ">odd</s:if> <s:else>even</s:else>">
				<td><s:property value="id" /></td>
				<td><s:property value="nome" /></td>
				<td><s:property value="idade" /></td>
				<td><s:property value="sexo" /></td>
				<td>
                <s:url var="linkEdicao" action="editarFuncionario">
					<s:param name="id" value="%{id}"></s:param>
				</s:url>
                <s:a href="%{linkEdicao}">Editar</s:a>
                </td>
				<td>
                <s:url var="deleteURL" action="deletarFuncionario">
					<s:param name="id" value="%{id}"></s:param>
				</s:url>
                <s:a href="%{deleteURL}">Deletar</s:a>
                </td>
			</tr>
		</s:iterator>
	</table>
	</div>
</s:if>
</body>
</html>