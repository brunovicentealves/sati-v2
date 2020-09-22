package com.br.sati.Service;

import com.br.sati.Model.HistoricoEquipamento;
import com.br.sati.Repository.HistoricoEquipamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HistoricoEquipamentoServiceImpl implements HistoricoEquipamentoService {

    @Autowired
    private HistoricoEquipamentoRepository historicoEquipamentoRepository;

    @Override
    public void salvarHistorico(HistoricoEquipamento historicoEquipamento) {
        historicoEquipamentoRepository.save(historicoEquipamento);
    }

    @Override
    public List<HistoricoEquipamento> listaHisotorico() {
        return (List<HistoricoEquipamento>) historicoEquipamentoRepository.findAll();
    }

    @Override
    public Optional<HistoricoEquipamento> RecuperarPorIdHistorico(Long idHisotorico) {
        return historicoEquipamentoRepository.findById(idHisotorico);
    }

    @Override
    public void atualizarHisotrico(HistoricoEquipamento historicoEquipamento) {
        historicoEquipamentoRepository.save(historicoEquipamento);
    }

    @Override
    public void ExcluirHistorico(Long idHistorico) {
            historicoEquipamentoRepository.deleteById(idHistorico);
    }
}
