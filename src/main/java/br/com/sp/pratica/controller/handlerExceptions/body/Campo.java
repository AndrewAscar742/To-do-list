package br.com.sp.pratica.controller.handlerExceptions.body;

public class Campo {
	private String campo;
	private String mensagem;
	
	public Campo() {
		// TODO Auto-generated constructor stub
	}

	public Campo(String campo, String mensagem) {
		super();
		this.campo = campo;
		this.mensagem = mensagem;
	}

	public String getCampo() {
		return campo;
	}

	public void setCampo(String campo) {
		this.campo = campo;
	}

	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}
	
	
}
