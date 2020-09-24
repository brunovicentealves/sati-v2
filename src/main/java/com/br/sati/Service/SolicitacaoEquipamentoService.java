package com.br.sati.Service;

import com.br.sati.Model.Equipamento;
import com.br.sati.Model.SolicitacaoEquipamento;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface SolicitacaoEquipamentoService {

    String salvarSolicitacaoEquipamento(SolicitacaoEquipamento solicitacaoEquipamento) throws SQLException;
    List<SolicitacaoEquipamento> listaSolicitacaoEquipamento() throws SQLException;
    Optional<SolicitacaoEquipamento> RecuperarPorIdSolicitacaoEquipamento(Long idSolicitacao);
    String atualizarSolicitacaoEquipamento(SolicitacaoEquipamento solicitacaoEquipamento);
    String ExcluirSolicitacaoEquipamento(Long idSolicitacao);
}
