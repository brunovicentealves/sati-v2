package com.br.sati.Model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.List;

@Entity
public class CentroCusto  implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    private long codccusto ;

    @NotBlank(message = "Campo obrigatorio !")
    private String nome;

    @OneToMany(mappedBy ="centroCusto")
    private List<Funcionario> funcionarios;


    public long getCodccusto() {
        return codccusto;
    }

    public void setCodccusto(long codccusto) {
        this.codccusto = codccusto;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<Funcionario> getFuncionarios() {
        return funcionarios;
    }

    public void setFuncionarios(List<Funcionario> funcionarios) {
        this.funcionarios = funcionarios;
    }
}
