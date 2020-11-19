package br.com.tab.osworks.domain.service;

import java.time.OffsetDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.tab.osworks.api.model.Comentario;
import br.com.tab.osworks.domain.model.Cliente;
import br.com.tab.osworks.domain.model.OrdemServico;
import br.com.tab.osworks.domain.model.StatusOrdemServico;
import br.com.tab.osworks.domain.repository.ClienteRepository;
import br.com.tab.osworks.domain.repository.ComentarioRepository;
import br.com.tab.osworks.domain.repository.OrdemServicoRepository;
import br.com.tab.osworks.exception.BusinessException;
import br.com.tab.osworks.exception.EntidadeNaoEncontradaException;

@Service
public class GestaoOrdemServicoService {

	@Autowired
	private OrdemServicoRepository ordemServicoRepository;
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	private ComentarioRepository comentarioRepo;
	
	
	public OrdemServico create(OrdemServico ordemServico) {
	
		Cliente cliente = clienteRepository.findById(ordemServico.getCliente().getId()).orElseThrow(()-> new BusinessException("Cliente nao encontrado"));
		
		ordemServico.setCliente(cliente);
		ordemServico.setStatusOrdemServico(StatusOrdemServico.ABERTA);
		ordemServico.setDataAbertura(OffsetDateTime.now());
		return ordemServicoRepository.save(ordemServico);
	}
	
	
	public Comentario adicionarComentario(Long oredemServicoId, String descricao) {
		
		OrdemServico ordemServico = ordemServicoRepository.findById(oredemServicoId).orElseThrow(
				()-> new EntidadeNaoEncontradaException("Ordem de serviço não encontrada")
				);
		
		Comentario comentario = new Comentario();
		comentario.setDataEnvio(OffsetDateTime.now());
		comentario.setDescricao(descricao);
		comentario.setOrdemServico(ordemServico);
		
		return comentarioRepo.save(comentario);
	}
	
	public void finalizar(Long ordemServicoId) {
		OrdemServico ordemServico = ordemServicoRepository.findById(ordemServicoId).orElseThrow(
				()-> new EntidadeNaoEncontradaException("Ordem de serviço não encontrada")
				);
		
		ordemServico.finalizar();
		
		ordemServicoRepository.save(ordemServico);
		
	}
	
	
}
