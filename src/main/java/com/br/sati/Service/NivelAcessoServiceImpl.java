package com.br.sati.Service;

import com.br.sati.Model.NivelAcesso;
import com.br.sati.Repository.NivelAcessoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Service
public class NivelAcessoServiceImpl implements NivelAcessoService {

    private NivelAcessoRepository nivelAcessoRepository;

    @Autowired
    public NivelAcessoServiceImpl(NivelAcessoRepository nivelAcessoRepository) {
        this.nivelAcessoRepository = nivelAcessoRepository;
    }

    @Override
    public String salvarNivelAcesso(NivelAcesso nivelAcesso) {
        try {
            nivelAcessoRepository.save(nivelAcesso);
            return "Cadastrado com sucesso NiVel de Acesso";
        }catch (Exception e ){
            return "Não foi possivel Cadastrar o Nivel de Acesso! --"+e.getMessage();
        }
    }

    @Override
    public List<NivelAcesso> listaNIvelAcesso() throws SQLException {
        return (List<NivelAcesso>) nivelAcessoRepository.findAll();
    }

    @Override
    public Optional<NivelAcesso> RecuperarPorIdNivelAcesso(String nomeAcesso) throws SQLException {
        return nivelAcessoRepository.findBynomeAcesso(nomeAcesso);
    }

    @Override
    public String atualizarNivelAcesso(NivelAcesso nivelAcesso) {
        try {
            nivelAcessoRepository.save(nivelAcesso);
            return "Atualizado com sucesso o NIvel de Acesso!";
        }catch (Exception e ){
            return "Não foi possivel Atualizar Nivel de Acesso! --"+e.getMessage();
        }
    }

    @Override
    public String ExcluirNivelAcesso(String nomeAcesso) throws SQLException {
        try {
            nivelAcessoRepository.deleteBynomeAcesso(nomeAcesso);
            return "Excluido com sucesso Nivel de Acesso";
        }catch (Exception e ){
            return "Não foi possivel excluir pois esse dados pode estar sendo usado em outro lugar do sistema ";
        }
    }
}
