package br.com.carlos.clothes_store.core.exceptions;

import org.springframework.validation.FieldError;

import jakarta.persistence.EntityNotFoundException;

public class SenhasNaoConferemException extends EntityNotFoundException{

    public SenhasNaoConferemException(String mensagem, FieldError fieldError) {
        super(mensagem);
    }
}