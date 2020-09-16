package com.br.sati.Service;

import com.br.sati.Model.Equipamento;

import java.util.List;
import java.util.Optional;

public interface EquipamentoService {

    void salvar(Equipamento equipamento);
    List<Equipamento> lista();
    Optional<Equipamento> RecuperarPorId(Long chapa);
    void atualizar(Equipamento equipamento);
    void Excluir(Long chapa);
}
