package com.br.sati.Model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
public class Equipamento implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id ;
    @NotBlank(message = "Campo obrigatorio !")
    private  String nomeEquipamento;
    @NotBlank(message = "Campo obrigatorio !")
    private  String descricao;

    @OneToMany(mappedBy = "equipamento")
    private List<SolicitacaoEquipamento> solicitacaoEquipamentoList;

    @OneToMany(mappedBy = "equipamento")
    private List<HistoricoEquipamento> historicoprodutoList;

    private Date data;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNomeEquipamento() {
        return nomeEquipamento;
    }

    public void setNomeEquipamento(String nomeEquipamento) {
        this.nomeEquipamento = nomeEquipamento;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public List<SolicitacaoEquipamento> getSolicitacaoEquipamentoList() {
        return solicitacaoEquipamentoList;
    }

    public void setSolicitacaoEquipamentoList(List<SolicitacaoEquipamento> solicitacaoEquipamentoList) {
        this.solicitacaoEquipamentoList = solicitacaoEquipamentoList;
    }

    public List<HistoricoEquipamento> getHistoricoprodutoList() {
        return historicoprodutoList;
    }

    public void setHistoricoprodutoList(List<HistoricoEquipamento> historicoprodutoList) {
        this.historicoprodutoList = historicoprodutoList;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }
}
