package com.br.sati.Model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Funcionario {

    @Id
    private long chapa;
    private String nome;
    private String nomeFuncao;

    @ManyToOne
    @JoinColumn(name = "codccusto", nullable = false)
    private CentroCusto centroCusto;

    public long getChapa() {
        return chapa;
    }

    public void setChapa(long chapa) {
        this.chapa = chapa;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getNomeFuncao() {
        return nomeFuncao;
    }

    public void setNomeFuncao(String nomeFuncao) {
        this.nomeFuncao = nomeFuncao;
    }
}
