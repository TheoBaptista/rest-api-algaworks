package br.com.tab.osworks.domain.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.tab.osworks.domain.model.Cliente;

@Repository
public interface ClienteRepository extends CrudRepository<Cliente, Long> {
	
//	Iterable<Cliente> findByNome(String nome);
	
//	Iterable<Cliente> findByContaining(String nome);
	
	Cliente findByEmail(String email);

}
