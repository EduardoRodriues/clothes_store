package br.com.carlos.clothes_store.core.enums;

public enum Tamanho {
    PP("PP"),
    P("P"),
    M("M"),
    G("G"),
    GG("GG"),
    X("X"),
    XX("XX");

    private final String nome;

    Tamanho(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }
}

