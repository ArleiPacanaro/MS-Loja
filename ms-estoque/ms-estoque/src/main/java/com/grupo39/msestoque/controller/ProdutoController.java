package com.grupo39.msestoque.controller;

import com.grupo39.msestoque.entity.Produto;
import com.grupo39.msestoque.request.ProdutoRequest;
import com.grupo39.msestoque.response.ProdutoResponse;
import com.grupo39.msestoque.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/produto")
public class ProdutoController {

    @Autowired
    private ProdutoService produtoService;

    @GetMapping
    public ResponseEntity<List<ProdutoResponse>> listarProdutos() {
        return produtoService.listarProdutos();
    }

    @GetMapping("/{produtoId}")
    public ResponseEntity<?> listarUmProduto(@PathVariable Integer produtoId) {
        return produtoService.listarUmProduto(produtoId);
    }
    @PostMapping
    public Produto cadastrarProduto(@RequestBody ProdutoRequest produto) {
        return produtoService.cadastrarProduto(produto);
    }

    @PutMapping("/{produtoId}")
    public Produto atualizarProduto(
            @PathVariable Integer produtoId, @RequestBody ProdutoRequest novoProduto) {

        return produtoService.atualizarProduto(produtoId, novoProduto);
    }

    @DeleteMapping("/{produtoId}")
    public void excluirProduto(@PathVariable Integer produtoId) {
        produtoService.excluirProduto(produtoId);
    }

    @PutMapping("/atualizar/estoque/{produtoId}/{quantidade}")
    public Produto atualizarEstoque(
            @PathVariable Integer produtoId, @PathVariable int quantidade) {
        return produtoService.atualizarEstoque(produtoId,quantidade);
    }

}
