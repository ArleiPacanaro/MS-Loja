package com.grupo39.msestoque.response;

import com.grupo39.msestoque.entity.Produto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import java.math.BigDecimal;

@Getter
@AllArgsConstructor
public class ProdutoResponse {


    private Integer id;
    private String nome;
    private String descricao;
    private int quantidade_estoque;
    private BigDecimal preco;


    public ProdutoResponse(Produto p) {

        this.id         = p.getId();
        this.nome       = p.getNome();
        this.descricao  = p.getDescricao();
        this.preco      = p.getPreco();

    }
}
