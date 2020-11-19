package br.com.tab.osworks.api.controller;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.tab.osworks.api.model.Comentario;
import br.com.tab.osworks.api.model.ComentarioInput;
import br.com.tab.osworks.api.model.ComentarioModel;
import br.com.tab.osworks.domain.model.OrdemServico;
import br.com.tab.osworks.domain.repository.OrdemServicoRepository;
import br.com.tab.osworks.domain.service.GestaoOrdemServicoService;
import br.com.tab.osworks.exception.EntidadeNaoEncontradaException;

@RestController
@RequestMapping("/ordens-servico/{ordemServicoId}/comentarios")
public class ComentarioController {
	
	@Autowired
	private OrdemServicoRepository ordemServicoRepository;
	
	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private GestaoOrdemServicoService gestaoOrdemServico;
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ComentarioModel adicionar(@PathVariable Long ordemServicoId,@Valid @RequestBody ComentarioInput comentarioInput) {
		Comentario comentario = gestaoOrdemServico.adicionarComentario(ordemServicoId, comentarioInput.getDescricao());
	
	return toModel(comentario);
	}

	private ComentarioModel toModel(Comentario comentario) {
		return modelMapper.map(comentario, ComentarioModel.class);
				
	}
	
	public List<ComentarioModel> listar(@PathVariable Long ordemServicoId){
		OrdemServico ordemServico = ordemServicoRepository.findById(ordemServicoId).orElseThrow(() -> new EntidadeNaoEncontradaException("Ordem de Serviço não encontrada"));
		
		return toCollectionModel(ordemServico.getComentarios());
	}

	private List<ComentarioModel> toCollectionModel(List<Comentario> comentarios) {

	return comentarios.stream().map(comentario -> toModel(comentario)).collect(Collectors.toList());
	
	}
	
	
}
