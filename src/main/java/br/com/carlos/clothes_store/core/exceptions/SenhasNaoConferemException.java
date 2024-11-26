package br.com.carlos.clothes_store.core.exceptions;

import org.springframework.validation.FieldError;

public class SenhasNaoConferemException extends ValidacaoException{

    public SenhasNaoConferemException(String mensagem, FieldError fieldError) {
        super(mensagem, fieldError);
    }
}