package br.com.tab.osworks.api.exceptionhandler;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class ResponseProblem {

	private Integer satatus;
	private OffsetDateTime dataHora;
	private String titulo;
	private List<Campo> campos;
	
	
	public List<Campo> getCampos() {
		return campos;
	}
	
	public void setCampos(List<Campo> campos) {
		this.campos = campos;
	}
	
	
	public static class Campo{
		
		private String nome;
		private String mensagem;
		
		
		
		public Campo(String nome, String mensagem) {
			super();
			this.nome = nome;
			this.mensagem = mensagem;
		}
		public String getNome() {
			return nome;
		}
		public void setNome(String nome) {
			this.nome = nome;
		}
		public String getMensagem() {
			return mensagem;
		}
		public void setMensagem(String mensagem) {
			this.mensagem = mensagem;
		}
		
	}
	
	
	
	public Integer getSatatus() {
		return satatus;
	}
	public void setSatatus(Integer satatus) {
		this.satatus = satatus;
	}
	public OffsetDateTime getDataHora() {
		return dataHora;
	}
	public void setDataHora(OffsetDateTime dataHora) {
		this.dataHora = dataHora;
	}
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	
	
	
}
