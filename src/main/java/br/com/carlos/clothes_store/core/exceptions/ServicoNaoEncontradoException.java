package br.com.carlos.clothes_store.core.exceptions;

import jakarta.persistence.EntityNotFoundException;

public class ServicoNaoEncontradoException extends EntityNotFoundException {

    public ServicoNaoEncontradoException(String mensagem) {
        super(mensagem);
    }
    
}