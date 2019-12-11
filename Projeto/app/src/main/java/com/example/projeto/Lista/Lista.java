package com.example.projeto.Lista;

import java.io.Serializable;

public class Lista implements Serializable {
    private Integer id;
    private String nome;
    private String nota;
    private boolean checagem;

    public boolean isChecagem() {
        return checagem;
    }
    public void setChecagem(boolean checagem) {
        this.checagem = checagem;
    }

    public String getNota() {
        return nota;
    }

    public void setNota(String nota) {
        this.nota = nota;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public String toString(){
        return nome;
    }
}
