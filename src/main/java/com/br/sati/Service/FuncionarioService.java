package com.br.sati.Service;

import com.br.sati.Model.Funcionario;

import java.util.List;
import java.util.Optional;

public interface FuncionarioService {

    void salvarFuncionario(Funcionario funcionario);
    List<Funcionario> listaFuncionario();
    Optional<Funcionario> RecuperarPorIdFuncionario(Long chapa);
    void atualizarFuncionario(Funcionario funcionario);
    void ExcluirFuncionario(Long id);
}
