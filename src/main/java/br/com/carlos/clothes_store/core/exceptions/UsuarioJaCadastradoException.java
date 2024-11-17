package br.com.carlos.clothes_store.core.exceptions;

import org.springframework.validation.FieldError;

import jakarta.persistence.EntityNotFoundException;

public class UsuarioJaCadastradoException extends EntityNotFoundException {
    
    public UsuarioJaCadastradoException(String mensagem, FieldError fieldError) {
        super(mensagem);
    }
}