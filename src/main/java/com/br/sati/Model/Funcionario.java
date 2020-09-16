package com.br.sati.Model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

@Entity
public class Funcionario  implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @NotNull
    @NotBlank(message = "Campo obrigatorio !")
    private long chapa;
    @NotBlank(message = "Campo obrigatorio !")
    private String nome;
    @NotBlank(message = "Campo obrigatorio !")
    private String nomeFuncao;

    @ManyToOne
    @JoinColumn(name = "codccusto", nullable = false)
    private CentroCusto centroCusto;


    @OneToMany(mappedBy = "funcionario")
    private List<SolicitacaoEquipamento> solicitacaoEquipamentoList;

    @OneToMany(mappedBy = "funcionario")
    private List<HistoricoEquipamento> historicoprodutos;


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

    public CentroCusto getCentroCusto() {
        return centroCusto;
    }

    public void setCentroCusto(CentroCusto centroCusto) {
        this.centroCusto = centroCusto;
    }

    public List<SolicitacaoEquipamento> getSolicitacaoEquipamentoList() {
        return solicitacaoEquipamentoList;
    }

    public void setSolicitacaoEquipamentoList(List<SolicitacaoEquipamento> solicitacaoEquipamentoList) {
        this.solicitacaoEquipamentoList = solicitacaoEquipamentoList;
    }

    public List<HistoricoEquipamento> getHistoricoprodutos() {
        return historicoprodutos;
    }

    public void setHistoricoprodutos(List<HistoricoEquipamento> historicoprodutos) {
        this.historicoprodutos = historicoprodutos;
    }
}
