package com.br.sati.Model;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
@Component
@Entity
public class SolicitacaoEquipamento implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
     private long idSolicitacao;

    @DateTimeFormat(pattern = "dd-MM-yyyy HH:mm")
    @Column(columnDefinition="DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataInicio;

    @DateTimeFormat(pattern = "dd-MM-yyyy HH:mm")
    @Column(columnDefinition="DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataEncerramento;

    private String status;

    private String descricao;

    private String logUsuario;

    @ManyToOne
    @JoinColumn(name = "codFuncionario", nullable = false)
    private Funcionario funcionario;

    @ManyToOne
    @JoinColumn(name = "codEquipamento", nullable = false)
    private Equipamento equipamento;


    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public long getIdSolicitacao() {
        return idSolicitacao;
    }

    public void setIdSolicitacao(long idSolicitacao) {
        this.idSolicitacao = idSolicitacao;
    }

    public Date getData() {
        return dataInicio;
    }

    public void setData(Date data) {
        this.dataInicio = data;
    }

    public Date getDataEncerramento() {
        return dataEncerramento;
    }

    public void setDataEncerramento(Date dataEncerramento) {
        this.dataEncerramento = dataEncerramento;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Funcionario getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }

    public Equipamento getEquipamento() {
        return equipamento;
    }

    public void setEquipamento(Equipamento equipamento) {
        this.equipamento = equipamento;

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
}
