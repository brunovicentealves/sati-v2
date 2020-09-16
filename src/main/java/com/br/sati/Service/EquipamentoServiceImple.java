package com.br.sati.Service;

import com.br.sati.Model.Equipamento;
import com.br.sati.Repository.EquipamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EquipamentoServiceImple implements EquipamentoService {

    private EquipamentoRepository equipamentoRepository;

    @Autowired
    public EquipamentoServiceImple(EquipamentoRepository equipamentoRepository) {
        this.equipamentoRepository = equipamentoRepository;
    }

    @Override
    public void salvar(Equipamento equipamento) {
        equipamentoRepository.save(equipamento);
    }

    @Override
    public List<Equipamento> lista() {
        return (List<Equipamento>) equipamentoRepository.findAll();
    }

    @Override
    public Optional<Equipamento> RecuperarPorId(Long id) {
        return equipamentoRepository.findById(id);
    }

    @Override
    public void atualizar(Equipamento equipamento) {
        equipamentoRepository.save(equipamento);
    }

    @Override
    public void Excluir(Long id) {
        equipamentoRepository.deleteById(id);
    }
}
