package com.br.sati.Dto;

import java.util.Date;

public class EncerramentoSolicitacao {

    private Long idSolicitacao;
    private Date dataInicio;
    private String dataEncerramento;
    private String status;
    private String descricao;
    private String logUsuario;
    private String funcionario;
    private Long funcionarioChapa;
    private String funcionarioNome;
    private String funcionarioCentroCustoNome;
    private String equipamentoNome;



    public Long getIdSolicitacao() {
        return idSolicitacao;
    }

    public void setIdSolicitacao(Long idSolicitacao) {
        this.idSolicitacao = idSolicitacao;
    }

    public Date getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(Date dataInicio) {
        this.dataInicio = dataInicio;
    }

    public String getDataEncerramento() {
        return dataEncerramento;
    }

    public void setDataEncerramento(String dataEncerramento) {
        this.dataEncerramento = dataEncerramento;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getLogUsuario() {
        return logUsuario;
    }

    public void setLogUsuario(String logUsuario) {
        this.logUsuario = logUsuario;
    }

    public Long getFuncionarioChapa() {
        return funcionarioChapa;
    }

    public void setFuncionarioChapa(Long funcionarioChapa) {
        this.funcionarioChapa = funcionarioChapa;
    }

    public String getFuncionarioNome() {
        return funcionarioNome;
    }

    public void setFuncionarioNome(String funcionarioNome) {
        this.funcionarioNome = funcionarioNome;
    }

    public String getFuncionarioCentroCustoNome() {
        return funcionarioCentroCustoNome;
    }

    public void setFuncionarioCentroCustoNome(String funcionarioCentroCustoNome) {
        this.funcionarioCentroCustoNome = funcionarioCentroCustoNome;
    }

    public String getEquipamentoNome() {
        return equipamentoNome;
    }

    public void setEquipamentoNome(String equipamentoNome) {
        this.equipamentoNome = equipamentoNome;
    }
}
