package com.br.sati.Service;

import com.br.sati.Model.Funcionario;
import com.br.sati.Model.HistoricoEquipamento;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface HistoricoEquipamentoService {

    String salvarHistorico(HistoricoEquipamento historicoEquipamento);
    List<HistoricoEquipamento> listaHistorico() throws SQLException;
    Optional<HistoricoEquipamento> RecuperarPorIdHistorico(Long idHisotorico) throws SQLException;
    String atualizarHistorico(HistoricoEquipamento historicoEquipamento);
    String ExcluirHistorico(Long idHistorico);
}
