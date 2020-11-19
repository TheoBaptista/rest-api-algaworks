package br.com.tab.osworks.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.tab.osworks.domain.model.Cliente;
import br.com.tab.osworks.domain.repository.ClienteRepository;
import br.com.tab.osworks.exception.BusinessException;

@Service
public class CrudClienteService {

	@Autowired
	private ClienteRepository clienteRepository;
	
	public Cliente save(Cliente cliente) {
		
		Cliente clienteExistente = clienteRepository.findByEmail(cliente.getEmail());
		
		if(clienteExistente != null && !clienteExistente.equals(cliente)) {
			throw new BusinessException("Já existe um cliente cadastro com este e-mail");
		}
		
		
		return clienteRepository.save(cliente);
	}
	
	public void excluir(Long clienteId) {
		clienteRepository.deleteById(clienteId);
	}
	
}
