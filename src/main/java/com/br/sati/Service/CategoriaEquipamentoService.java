package com.br.sati.Service;

import com.br.sati.Model.CategoriaEquipamento;
import com.br.sati.Model.Equipamento;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface CategoriaEquipamentoService {

    String salvarCategoriaEquipamento(CategoriaEquipamento categoriaEquipamento);
    List<CategoriaEquipamento> listaCategoriaEquipamento() throws SQLException;
    Optional<CategoriaEquipamento> RecuperarPorIdCategoriaEquipamento(Long id) throws SQLException;
    String atualizarCategoriaEquipamento(CategoriaEquipamento categoriaEquipamento);
    String ExcluirCategoriaEquipamento(Long id) throws SQLException;
}
