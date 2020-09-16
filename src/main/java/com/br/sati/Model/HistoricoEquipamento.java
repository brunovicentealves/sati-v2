package com.br.sati.Model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
public class HistoricoEquipamento {

    @Id
    @GeneratedValue(strategy =GenerationType.IDENTITY )
    private long idHistoricoProduto;

    @ManyToOne
    @JoinColumn(name = "codEquipamento",nullable = false)
    private Equipamento equipamento;

    @ManyToOne
    @JoinColumn(name = "codFuncionario",nullable = false)
    private Funcionario funcionario;

    @NotNull
    @NotBlank(message = "Preechar o campo com Patrimonio da Máquina")
    private String patrimonioMaquina;

    @NotNull
    private Date data;


    public long getIdHistoricoProduto() {
        return idHistoricoProduto;
    }

    public void setIdHistoricoProduto(long idHistoricoProduto) {
        this.idHistoricoProduto = idHistoricoProduto;
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

    public String getPatrimonioMaquina() {
        return patrimonioMaquina;
    }

    public void setPatrimonioMaquina(String patrimonioMaquina) {
        this.patrimonioMaquina = patrimonioMaquina;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }
}
