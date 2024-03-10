package com.grupo39.msestoque.service;

import com.grupo39.msestoque.entity.Produto;
import com.grupo39.msestoque.repository.ProdutoRepository;
import com.grupo39.msestoque.request.ProdutoRequest;
import com.grupo39.msestoque.response.ProdutoResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    public ResponseEntity<List<ProdutoResponse>> listarProdutos() {

        List<Produto> produtos = produtoRepository.findAll();
        List<ProdutoResponse> produtosResponse = new ArrayList<>();

        produtos.forEach(p -> produtosResponse.add(new ProdutoResponse(p)));

        return ResponseEntity.ok(produtosResponse);
    }

    public ResponseEntity<?> listarUmProduto(Integer produtoId) {


        Produto produto = produtoRepository.findById(produtoId).orElse(null);
        if (produto != null) {
            return ResponseEntity.ok(produto); // Retorna 200 OK com o produto encontrado
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Produto não encontrado");
        }
    }

    public Produto cadastrarProduto(ProdutoRequest produtoRequest) {

        return produtoRepository.save(produtoRequest.toProduto());
    }

    public Produto atualizarProduto(Integer produtoId, ProdutoRequest produtoRequest) {
        Produto produtoExistente = produtoRepository.findById(produtoId).orElse(null);

        if (produtoExistente != null) {
            produtoExistente.setNome(produtoRequest.getNome());
            produtoExistente.setDescricao(produtoRequest.getDescricao());
            produtoExistente.setQuantidade_estoque(produtoRequest.getQuantidade_estoque());
            produtoExistente.setPreco(produtoRequest.getPreco());

            return produtoRepository.save(produtoExistente);
        } else {
            throw new NoSuchElementException("Produto não encontrado");
        }
    }

    public void excluirProduto(Integer produtoId) {
        Produto produtoExistente = produtoRepository.findById(produtoId).orElse(null);

        if (produtoExistente != null) {
            produtoRepository.delete(produtoExistente);
        } else {
            throw new NoSuchElementException("Produto não encontrado");
        }
    }

    public Produto atualizarEstoque(Integer produtoId, int quantidade) {
        Produto produto = produtoRepository.findById(produtoId).orElse(null);
        if (produto != null) {
            produto.setQuantidade_estoque(produto.getQuantidade_estoque() - quantidade);

            return produtoRepository.save(produto);
        }
        return null;
    }
}
