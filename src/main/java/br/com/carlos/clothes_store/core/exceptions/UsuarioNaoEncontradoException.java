package br.com.carlos.clothes_store.core.exceptions;

import jakarta.persistence.EntityNotFoundException;

public class UsuarioNaoEncontradoException extends EntityNotFoundException {

    public UsuarioNaoEncontradoException(String mensagem) {
        super(mensagem);
    }
    
}