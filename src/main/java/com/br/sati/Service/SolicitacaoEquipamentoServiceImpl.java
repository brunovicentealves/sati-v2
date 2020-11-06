package com.br.sati.Service;

import com.br.sati.Model.SolicitacaoEquipamento;
import com.br.sati.Repository.SolicitacaoEquipamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
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
    public String salvarSolicitacaoEquipamento(SolicitacaoEquipamento solicitacaoEquipamento) throws SQLException {
    try {
        solicitacaoEquipamentoRepository.save(solicitacaoEquipamento);
        return "Cadastrado Com Sucesso Solictação!";
    }catch (Exception e ){
        return "Não possivel cadastrar Solicitação! --"+e.getMessage();
    }
}

    @Override
    public List<SolicitacaoEquipamento> listaSolicitacaoEquipamento()  throws SQLException {
        return (List<SolicitacaoEquipamento>) solicitacaoEquipamentoRepository.findAll();
    }

    @Override
    public Optional<SolicitacaoEquipamento> RecuperarPorIdSolicitacaoEquipamento(Long idSolicitacao) {
        return solicitacaoEquipamentoRepository.findById(idSolicitacao);
    }

    @Override
    public String atualizarSolicitacaoEquipamento(SolicitacaoEquipamento solicitacaoEquipamento) {
        try {
            solicitacaoEquipamentoRepository.save(solicitacaoEquipamento);
            return "Atualizado com sucesso  Solicitação";
        }catch (Exception e ){
        return "Não foi possivel Atualizar essa Solicitação! --"+e.getMessage();
        }
    }

    @Override
    public String ExcluirSolicitacaoEquipamento(Long idSolicitacao) {
            try {
                solicitacaoEquipamentoRepository.deleteById(idSolicitacao);
                return "Solicitação Excluida com Sucesso";
            }catch (Exception e ){
                return "Não foi possivel Excluir essa solicitacao ela pode estar sendo usada em outra parte do sistema f";
            }
    }

    public List<SolicitacaoEquipamento> listaSolicitacaoEquipamentoStatus(String status) throws SQLException{
      return  solicitacaoEquipamentoRepository.findByStatusOrderByDataInicioDesc(status);
    }

    public Integer quantidadeSolicitacao(String status){
        return solicitacaoEquipamentoRepository.quantidadeSolicitacaoAprovada(status);
    }

    public List<SolicitacaoEquipamento> listaSolicitacaoAprovadaeSolicitadoFornecedor(String status1,String status2) throws SQLException{
        return solicitacaoEquipamentoRepository.solicitacaoAprovadoSolicitadoForncedor(status1,status2);
    }

}
