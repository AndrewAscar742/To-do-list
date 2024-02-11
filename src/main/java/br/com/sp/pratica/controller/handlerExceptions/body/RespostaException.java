package br.com.sp.pratica.controller.handlerExceptions.body;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(value = Include.NON_NULL)
public class RespostaException {
	private String titulo;
	private StringBuffer Url;
	private Integer status;
	private String recurso;
	private String dataRequisicao;
	private List<Campo> campos;
	
	public RespostaException() {
		// TODO Auto-generated constructor stub
	}
	
	public RespostaException(HttpStatus status, List<Campo> campos) {
		this.titulo = "Campos inválidos, tente novamente";
		this.status = status.value();
		this.dataRequisicao = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"));
		this.campos = campos;
	}

	public RespostaException(String message, int value, HttpServletRequest requestPath) {
		this.titulo = message;
		this.status = value;
		this.Url = requestPath.getRequestURL();
		this.recurso = requestPath.getRequestURI();
		this.dataRequisicao = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"));
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public StringBuffer getUrl() {
		return Url;
	}

	public void setUrl(StringBuffer url) {
		Url = url;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getRecurso() {
		return recurso;
	}

	public void setRecurso(String recurso) {
		this.recurso = recurso;
	}

	public String getDataRequisicao() {
		return dataRequisicao;
	}

	public void setDataRequisicao(String dataRequisicao) {
		this.dataRequisicao = dataRequisicao;
	}

	public List<Campo> getCampos() {
		return campos;
	}

	public void setCampos(List<Campo> campos) {
		this.campos = campos;
	}
	
	
}
