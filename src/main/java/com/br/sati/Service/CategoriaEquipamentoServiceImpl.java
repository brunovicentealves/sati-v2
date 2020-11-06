package com.br.sati.Service;

import com.br.sati.Model.CategoriaEquipamento;
import com.br.sati.Model.Equipamento;
import com.br.sati.Model.Funcionario;
import com.br.sati.Repository.CategoriaEquipamentoRepositoy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Service
public class CategoriaEquipamentoServiceImpl implements CategoriaEquipamentoService {

    private CategoriaEquipamentoRepositoy categoriaEquipamentoRepositoy;

    @Autowired
    public CategoriaEquipamentoServiceImpl(CategoriaEquipamentoRepositoy categoriaEquipamentoRepositoy) {
        this.categoriaEquipamentoRepositoy = categoriaEquipamentoRepositoy;
    }

    @Override
    public String salvarCategoriaEquipamento(CategoriaEquipamento categoriaEquipamento) {
        try {
            categoriaEquipamentoRepositoy.save(categoriaEquipamento);
            return "Salvado com sucesso o Categoria !";
        }catch (Exception e){
            return "Não foi possivel salvar ! -- "+e.getMessage();
        }
    }

    @Override
    public List<CategoriaEquipamento> listaCategoriaEquipamento() throws SQLException {
        return (List<CategoriaEquipamento>) categoriaEquipamentoRepositoy.findAll();
    }

    @Override
    public Optional<CategoriaEquipamento> RecuperarPorIdCategoriaEquipamento(Long id) throws SQLException {
        return categoriaEquipamentoRepositoy.findById(id);
    }

    @Override
    public String atualizarCategoriaEquipamento(CategoriaEquipamento categoriaEquipamento) {
        try {
            categoriaEquipamentoRepositoy.save(categoriaEquipamento);
            return "Atualizado com sucesso Categoria";
        }catch (Exception e ){
            return " Não foi possivel Atualizar o Funcionario! --"+e.getMessage();
        }
    }

    @Override
    public String ExcluirCategoriaEquipamento(Long id) throws SQLException {
        try {
            categoriaEquipamentoRepositoy.deleteById(id);
            return "Excluido o Categoria com Sucesso";
        }catch (Exception e ){
            return "Não foi possivel excluir por estar sendo os dados em outra parte da aplicação!";
        }
    }
}
