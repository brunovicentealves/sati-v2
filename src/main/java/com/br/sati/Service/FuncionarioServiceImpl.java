package com.br.sati.Service;

import com.br.sati.Model.Funcionario;
import com.br.sati.Repository.FuncionarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public void salvarFuncionario(Funcionario funcionario) {
        funcionarioRepository.save(funcionario);

    }

    @Override
    public List<Funcionario> listaFuncionario() {
        return (List<Funcionario>) funcionarioRepository.findAll();
    }

    @Override
    public Optional<Funcionario> RecuperarPorIdFuncionario(Long chapa) {
        return funcionarioRepository.findById(chapa);
    }

    @Override
    public void atualizarFuncionario(Funcionario funcionario) {
        funcionarioRepository.save(funcionario);
    }

    @Override
    public void ExcluirFuncionario(Long chapa) {
        funcionarioRepository.deleteById(chapa);
    }
}
