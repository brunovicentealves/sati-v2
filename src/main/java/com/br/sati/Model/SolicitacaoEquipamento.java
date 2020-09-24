package com.br.sati.Model;

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

    @Column(columnDefinition="DATE")
    @Temporal(TemporalType.DATE)
    private Date data;

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
        return data;
    }

    public void setData(Date data) {
        this.data = data;
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
