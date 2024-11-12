package br.com.carlos.clothes_store.web.dtos;

public class FlashMessage {
    
    private String mensagem;

    private String classeCss;

    public FlashMessage(String classeCss, String mensagem) {
        this.classeCss = classeCss;
        this.mensagem = mensagem;
    }

	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}

	public String getClasseCss() {
		return classeCss;
	}

	public void setClasseCss(String classeCss) {
		this.classeCss = classeCss;
	}
}
