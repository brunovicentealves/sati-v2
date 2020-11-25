package com.br.sati.Service;

import com.br.sati.Model.HistoricoEquipamento;
import com.br.sati.Repository.HistoricoEquipamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.xml.crypto.Data;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static com.br.sati.Util.DataFormatada.gerarData;

@Service
public class HistoricoEquipamentoServiceImpl implements HistoricoEquipamentoService {

    @Autowired
    private HistoricoEquipamentoRepository historicoEquipamentoRepository;

    @Override
    public String salvarHistorico(HistoricoEquipamento historicoEquipamento) {

        try {
            historicoEquipamento.setDataCriacao(gerarData());
            historicoEquipamento.setLogUsuario(SecurityContextHolder.getContext().getAuthentication().getName());
            historicoEquipamentoRepository.save(historicoEquipamento);
            return "Histórico Salvo com Sucesso";
        }catch (Exception e){
            return "Não foi possivel Salvar esse Histórico!"+e.getMessage();
        }
    }

    @Override
    public List<HistoricoEquipamento> listaHistorico() throws SQLException {
        return (List<HistoricoEquipamento>) historicoEquipamentoRepository.findAll();
    }

    @Override
    public Optional<HistoricoEquipamento> RecuperarPorIdHistorico(Long idHisotorico) throws SQLException {
        return historicoEquipamentoRepository.findById(idHisotorico);
    }

    @Override
    public String atualizarHistorico(HistoricoEquipamento historicoEquipamento) {
        try {
            historicoEquipamentoRepository.save(historicoEquipamento);
            return "Atualizado com sucesso o Histórico";
        }catch (Exception e){
            return "Não foi possivel Atualizar esse histórico! --"+e.getMessage();
        }

    }

    @Override
    public String ExcluirHistorico(Long idHistorico) {
        try {
            historicoEquipamentoRepository.deleteById(idHistorico);
            return "Excluido com sucesso o Histórico";
        }catch (Exception e ){
            return "Não foi Poessivel Excluir Historico de Equipamento!";
        }

    }
}
