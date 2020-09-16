package com.br.sati.Service;

import com.br.sati.Model.SolicitacaoEquipamento;
import com.br.sati.Repository.SolicitacaoEquipamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class SolicitacaoEquipamentoServiceImpl implements SolicitacaoEquipamentoService {

    private SolicitacaoEquipamentoRepository solicitacaoEquipamentoRepository;

    @Autowired
    public SolicitacaoEquipamentoServiceImpl(SolicitacaoEquipamentoRepository solicitacaoEquipamentoRepository) {
        this.solicitacaoEquipamentoRepository = solicitacaoEquipamentoRepository;
    }

    @Override
    public void salvarSolicitacaoEquipamento(SolicitacaoEquipamento solicitacaoEquipamento) {

        solicitacaoEquipamentoRepository.save(solicitacaoEquipamento);
    }

    @Override
    public List<SolicitacaoEquipamento> listaSolicitacaoEquipamento() {
        return (List<SolicitacaoEquipamento>) solicitacaoEquipamentoRepository.findAll();
    }

    @Override
    public Optional<SolicitacaoEquipamento> RecuperarPorIdSolicitacaoEquipamento(Long idSolicitacao) {
        return solicitacaoEquipamentoRepository.findById(idSolicitacao);
    }

    @Override
    public void atualizarSolicitacaoEquipamento(SolicitacaoEquipamento solicitacaoEquipamento) {
        solicitacaoEquipamentoRepository.save(solicitacaoEquipamento);
    }

    @Override
    public void ExcluirSolicitacaoEquipamento(Long idSolicitacao) {
            solicitacaoEquipamentoRepository.deleteById(idSolicitacao);
    }

    public List<SolicitacaoEquipamento> listaSolicitacaoEquipamentoStatus(String status){
      return  solicitacaoEquipamentoRepository.findByStatusOrderByDataDesc(status);
    }

}
