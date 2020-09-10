package com.br.sati.Model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
public class CentroCusto {
    @Id
    private long codccusto ;

    private String nome;

    @OneToMany(mappedBy ="centroCusto")
    private List<Funcionario> funcionarios;

}
