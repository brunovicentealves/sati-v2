package com.br.sati.Service;

import com.br.sati.Model.Equipamento;
import com.br.sati.Repository.EquipamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
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
    public String salvar(Equipamento equipamento) {
            try {
                equipamentoRepository.save(equipamento);
                return "Cadastrado Equipamento com sucesso!";
            }catch ( Exception e ){
                return "Não foi possivel Salvar ! --"+e.getMessage();
            }
    }

    @Override
    public List<Equipamento> lista() throws SQLException{
        return (List<Equipamento>) equipamentoRepository.findAll();
    }

    @Override
    public Optional<Equipamento> RecuperarPorId(Long id) throws SQLException {
        return equipamentoRepository.findById(id);
    }

    @Override
    public String atualizar(Equipamento equipamento) {
        try {
            equipamentoRepository.save(equipamento);
            return "Atualizado Equipamento com sucesso!";
        }catch ( Exception e ){
            return "Não foi possivel atualizar ! --"+e.getMessage();
        }

    }

    @Override
    public String Excluir(Long id) {
        try {
            equipamentoRepository.deleteById(id);
            return "Excuido Equipamento com sucesso!";
        }catch (Exception e ){
            return "Não foi excluido por que  esse dados estão sendo usados em outra parte do sistema!"+e.getMessage();
        }

    }
}
