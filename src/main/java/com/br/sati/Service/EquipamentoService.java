package com.br.sati.Service;

import com.br.sati.Model.Equipamento;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface EquipamentoService {

    String salvar(Equipamento equipamento);
    List<Equipamento> lista() throws SQLException;
    Optional<Equipamento> RecuperarPorId(Long chapa) throws SQLException;
    String atualizar(Equipamento equipamento);
    String Excluir(Long chapa) throws SQLException;
}
