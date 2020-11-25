package com.br.sati.Model;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
public class HistoricoEquipamento {

    @Id
    @GeneratedValue(strategy =GenerationType.IDENTITY )
    private long idHistorico;

    @ManyToOne
    @JoinColumn(name = "codEquipamento",nullable = false)
    private Equipamento equipamento;

    @ManyToOne
    @JoinColumn(name = "codFuncionario",nullable = false)
    private Funcionario funcionario;

    private String patrimonioEquipamento;

    @JsonFormat(pattern="dd/MM/yyyy HH:mm:ss")
    private Date dataCriacao;

    @JsonFormat(pattern="dd/MM/yyyy HH:mm:ss")
    private Date dataAlteracao;

    private String logUsuario;

    public long getIdHistorico() {
        return idHistorico;
    }

    public void setIdHistorico(long idHistoricoProduto) {
        this.idHistorico = idHistoricoProduto;
    }

    public Equipamento getEquipamento() {
        return equipamento;
    }

    public void setEquipamento(Equipamento equipamento) {
        this.equipamento = equipamento;
    }

    public Funcionario getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }

    public String getPatrimonioEquipamento() {
        return patrimonioEquipamento;
    }

    public void setPatrimonioEquipamento(String patrimonioEquipamento) {
        this.patrimonioEquipamento = patrimonioEquipamento;
    }

    public Date getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(Date dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public Date getDataAlteracao() {
        return dataAlteracao;
    }

    public void setDataAlteracao(Date dataAlteracao) {
        this.dataAlteracao = dataAlteracao;
    }

    public String getLogUsuario() {
        return logUsuario;
    }

    public void setLogUsuario(String logUsuario) {
        this.logUsuario = logUsuario;
    }
}
