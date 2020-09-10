package com.br.sati.Service;

import com.br.sati.Model.Produto;
import com.br.sati.Repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProdutoServiceImple implements ProdutoService {

    private ProdutoRepository produtoRepository;

    @Autowired
    public ProdutoServiceImple(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    @Override
    public void salvarProduto(Produto produto) {
        produtoRepository.save(produto);
    }

    @Override
    public List<Produto> listaProdutos() {
        return (List<Produto>) produtoRepository.findAll();
    }

    @Override
    public Optional<Produto> RecuperarPorIdProduto(Long id) {
        return produtoRepository.findById(id);
    }

    @Override
    public void atualizarProduto(Produto produto) {
        produtoRepository.save(produto);
    }

    @Override
    public void ExcluirProduto(Long id) {
        produtoRepository.deleteById(id);
    }
}
