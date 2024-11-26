package br.com.carlos.clothes_store.core.exceptions;

import org.springframework.validation.FieldError;

public class SenhaIncorretaException  extends ValidacaoException{

	public SenhaIncorretaException(String mensagem, FieldError fieldError) {
		super(mensagem, fieldError);
	}
    
}
