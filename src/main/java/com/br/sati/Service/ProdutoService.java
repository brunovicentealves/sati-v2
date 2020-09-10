package com.br.sati.Service;

import com.br.sati.Model.Produto;

import java.util.List;
import java.util.Optional;

public interface ProdutoService {

    void salvarProduto(Produto produto);
    List<Produto> listaProdutos();
    Optional<Produto> RecuperarPorIdProduto(Long id);
    void atualizarProduto(Produto produto);
    void ExcluirProduto(Long id);
}
