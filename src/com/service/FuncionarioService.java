package com.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.FuncionarioDAO;
import com.domain.Funcionario;

@Service(value="funcionarioServiceComponent")
public class FuncionarioService extends AbstractService {
	
	@Autowired(required=true)
	private FuncionarioDAO funcionarioDaoRepository;
	
	public void saveOrUpdate(Funcionario funcionario) {
		if (funcionario.getId() != null) {
			funcionarioDaoRepository.update(funcionario);
		} else {
			funcionarioDaoRepository.save(funcionario);
		}
	}
	
	public List<Funcionario> find(Funcionario funcionario) {
		return funcionarioDaoRepository.find(funcionario);
	}
	
	public Funcionario findById(Integer idFuncionario) {
		Funcionario funcionario = new Funcionario();
		funcionario.setId(idFuncionario);
		return funcionarioDaoRepository.find(funcionario).get(0);
	}
	
	public List<Funcionario> findAll() {
		return funcionarioDaoRepository.findAll();
	}
	
	public void deleteById(Integer funcionarioId) {
		Funcionario funcionario = new Funcionario();
		funcionario.setId(funcionarioId);
		funcionarioDaoRepository.delete(funcionario);
	}
	
}
