package com.br.sati.Service;

import com.br.sati.Model.Funcionario;
import com.br.sati.Model.HistoricoEquipamento;

import java.util.List;
import java.util.Optional;

public interface HistoricoEquipamentoService {

    void salvarHistorico(HistoricoEquipamento historicoEquipamento);
    List<HistoricoEquipamento> listaHisotorico();
    Optional<HistoricoEquipamento> RecuperarPorIdHistorico(Long idHisotorico);
    void atualizarHisotrico(HistoricoEquipamento historicoEquipamento);
    void ExcluirHistorico(Long idHistorico);
}
