package com.br.sati.Service;

import com.br.sati.Dto.Solicitacao;
import com.br.sati.Model.HistoricoEquipamento;
import com.br.sati.Model.SolicitacaoEquipamento;
import com.br.sati.Repository.HistoricoEquipamentoRepository;
import com.br.sati.Repository.SolicitacaoEquipamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static com.br.sati.Util.DataFormatada.formatandodata;
import static com.br.sati.Util.DataFormatada.gerarData;

@Service
public class SolicitacaoEquipamentoServiceImpl implements SolicitacaoEquipamentoService {

    private SolicitacaoEquipamentoRepository solicitacaoEquipamentoRepository;
    private SolicitacaoEquipamento solicitacaoEquipamento;
    private EquipamentoServiceImple equipamentoServiceImpl;
    private FuncionarioServiceImpl funcionarioServiceImpl;
    private HistoricoEquipamentoServiceImpl historicoEquipamentoServiceImpl;


    @Autowired
    public SolicitacaoEquipamentoServiceImpl(SolicitacaoEquipamentoRepository solicitacaoEquipamentoRepository, SolicitacaoEquipamento solicitacaoEquipamento, EquipamentoServiceImple equipamentoServiceImpl, FuncionarioServiceImpl funcionarioServiceImpl,HistoricoEquipamentoServiceImpl historicoEquipamentoServiceImpl)  {
        this.solicitacaoEquipamentoRepository = solicitacaoEquipamentoRepository;
        this.solicitacaoEquipamento = solicitacaoEquipamento;
        this.equipamentoServiceImpl = equipamentoServiceImpl;
        this.funcionarioServiceImpl = funcionarioServiceImpl;
        this.historicoEquipamentoServiceImpl=historicoEquipamentoServiceImpl;
    }

    @Override
    public String salvarSolicitacaoEquipamento(Solicitacao solicitacao) throws SQLException {
        try {
            SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
            SolicitacaoEquipamento solicitacaoEquipamento = new SolicitacaoEquipamento();
            solicitacaoEquipamento.setDataInicio(formatandodata(solicitacao.getDataInicio()));
            solicitacaoEquipamento.setDescricao(solicitacao.getDescricao());
            solicitacaoEquipamento.setEquipamento(equipamentoServiceImpl.RecuperarPorId(solicitacao.getIdEquipamento()).get());
            solicitacaoEquipamento.setFuncionario(funcionarioServiceImpl.RecuperarPorIdFuncionario(solicitacao.getIdFuncionario()).get());
            solicitacaoEquipamento.setStatus("Aguardando Aprovação");

            solicitacaoEquipamentoRepository.save(solicitacaoEquipamento);
            return "Cadastrado Com Sucesso Solictação!";
        } catch (Exception e) {
            return "Não possivel cadastrar Solicitação! --" + e.getMessage();
        }
    }

    @Override
    public List<SolicitacaoEquipamento> listaSolicitacaoEquipamento() throws SQLException {
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
        } catch (Exception e) {
            return "Não foi possivel Atualizar essa Solicitação! --" + e.getMessage();
        }
    }

    @Override
    public String ExcluirSolicitacaoEquipamento(Long idSolicitacao) {
        try {
            solicitacaoEquipamentoRepository.deleteById(idSolicitacao);
            return "Solicitação Excluida com Sucesso";
        } catch (Exception e) {
            return "Não foi possivel Excluir essa solicitacao ela pode estar sendo usada em outra parte do sistema f";
        }
    }

    public List<SolicitacaoEquipamento> listaSolicitacaoEquipamentoStatus(String status) throws SQLException {
        return solicitacaoEquipamentoRepository.findByStatusOrderByDataInicioDesc(status);
    }

    public Integer quantidadeSolicitacao(String status) {
        return solicitacaoEquipamentoRepository.quantidadeSolicitacaoAprovada(status);
    }

    public List<SolicitacaoEquipamento> listaSolicitacaoAprovadaeSolicitadoFornecedor(String status1, String status2) throws SQLException {
        return solicitacaoEquipamentoRepository.solicitacaoAprovadoSolicitadoForncedor(status1, status2);
    }

    public Integer quantidadeSolicitacaoaMesAberta() {
        Date date = new Date();
        return solicitacaoEquipamentoRepository.quantidadeSolicitacaoMesAberto(date);
    }


    public String encerrarSolicitacaoEquipamento(SolicitacaoEquipamento solicitacaoEquipamento) {
        HistoricoEquipamento historicoEquipamento = new HistoricoEquipamento();
        try {
            solicitacaoEquipamento.setLogUsuario(SecurityContextHolder.getContext().getAuthentication().getName());
            solicitacaoEquipamentoRepository.save(solicitacaoEquipamento);

            if(solicitacaoEquipamento.getEquipamento().getCategoria().getNomeCategoria().equals("Computador")){
                historicoEquipamento.setEquipamento(solicitacaoEquipamento.getEquipamento());
                historicoEquipamento.setFuncionario(solicitacaoEquipamento.getFuncionario());
                historicoEquipamentoServiceImpl.salvarHistorico(historicoEquipamento);
            }
            return "Encerrado  com sucesso  a Solicitação e  registrado histórico do equipamento";

        } catch (Exception e) {

            return "Não foi possivel Encerrar  essa Solicitação! --" + e.getMessage();
        }

    }

    public List<SolicitacaoEquipamento> solicitacaoprioridade() {
        return solicitacaoEquipamentoRepository.findSolicitacaoprioridade();
    }


}
