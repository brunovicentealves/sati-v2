package com.br.sati.Service;

import com.br.sati.Model.Equipamento;
import com.br.sati.Model.SolicitacaoEquipamento;

import java.util.List;
import java.util.Optional;

public interface SolicitacaoEquipamentoService {

    void salvarSolicitacaoEquipamento(SolicitacaoEquipamento solicitacaoEquipamento);
    List<SolicitacaoEquipamento> listaSolicitacaoEquipamento();
    Optional<SolicitacaoEquipamento> RecuperarPorIdSolicitacaoEquipamento(Long idSolicitacao);
    void atualizarSolicitacaoEquipamento(SolicitacaoEquipamento solicitacaoEquipamento);
    void ExcluirSolicitacaoEquipamento(Long idSolicitacao);
}
