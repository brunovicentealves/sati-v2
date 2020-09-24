package com.br.sati.Service;

import com.br.sati.Model.Funcionario;
import com.br.sati.Repository.FuncionarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Service
public class FuncionarioServiceImpl implements FuncionarioService {

    private FuncionarioRepository funcionarioRepository;

    @Autowired
    public FuncionarioServiceImpl(FuncionarioRepository funcionarioRepository) {
        this.funcionarioRepository = funcionarioRepository;
    }

    @Override
    public String salvarFuncionario(Funcionario funcionario) {

        try {
            funcionarioRepository.save(funcionario);
            return "Salvado com sucesso o Funcionario !";
        }catch (Exception e){
            return "Não foi possivel salvar ! -- "+e.getMessage();
        }
    }

    @Override
    public List<Funcionario> listaFuncionario() throws SQLException {
        return (List<Funcionario>) funcionarioRepository.findAll();
    }

    @Override
    public Optional<Funcionario> RecuperarPorIdFuncionario(Long chapa) throws SQLException{
        return funcionarioRepository.findById(chapa);
    }

    @Override
    public String atualizarFuncionario(Funcionario funcionario) {

        try {
            funcionarioRepository.save(funcionario);
            return "Atualizado com sucesso Funcionario";
        }catch (Exception e ){
            return " Não foi possivel Atualizar o Funcionario! --"+e.getMessage();
        }
    }

    @Override
    public String ExcluirFuncionario(Long chapa) {
        try {

            funcionarioRepository.deleteById(chapa);
            return "Excluido o Funcionário com Sucesso";
        }catch (Exception e ){
            return "Não foi possivel excluir por estar sendo os dados em outra parte da aplicação!";
        }

    }
}
