package br.com.tab.osworks.api.controller;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.tab.osworks.domain.model.Cliente;
import br.com.tab.osworks.domain.repository.ClienteRepository;
import br.com.tab.osworks.domain.service.CrudClienteService;

@RestController
@RequestMapping("/clientes")
public class ClienteController  {

	@Autowired
	private CrudClienteService crudService;
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@GetMapping
	public Iterable<Cliente> listar() {
	return clienteRepository.findAll();	
	}
	
	@GetMapping("/{clienteId}")
	public ResponseEntity<Cliente> show(@PathVariable Long ClienteId) {
		
	Optional<Cliente> cliente =	clienteRepository.findById(ClienteId);
	
	if(cliente.isPresent()) {
		return ResponseEntity.ok(cliente.get());
	}
	
	return ResponseEntity.notFound().build();
			}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Cliente adicionar(@Valid @RequestBody Cliente cliente) {
	 return	crudService.save(cliente);
	}
	
	@PutMapping("/{clienteId}")
	public ResponseEntity<Cliente> atualizar(@Valid @PathVariable Long clienteId, @RequestBody Cliente cliente){
		
		if(!clienteRepository.existsById(clienteId)) {
			return ResponseEntity.notFound().build();
		}
		
		cliente.setId(clienteId);
		cliente = crudService.save(cliente);
		
		return ResponseEntity.ok(cliente);
	}

	@DeleteMapping("/{clienteId}")
	public ResponseEntity<Void> remover(@PathVariable Long clienteId){
		
		if(!clienteRepository.existsById(clienteId)) {
			return ResponseEntity.notFound().build();
		}
		
		crudService.excluir(clienteId);
		
		return ResponseEntity.noContent().build();		
	}
	

}
	
	

