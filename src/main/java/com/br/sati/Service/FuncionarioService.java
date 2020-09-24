package com.br.sati.Service;

import com.br.sati.Model.Funcionario;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface FuncionarioService {

    String salvarFuncionario(Funcionario funcionario);
    List<Funcionario> listaFuncionario() throws SQLException;
    Optional<Funcionario> RecuperarPorIdFuncionario(Long chapa) throws SQLException;
    String atualizarFuncionario(Funcionario funcionario);
    String ExcluirFuncionario(Long id);
}
