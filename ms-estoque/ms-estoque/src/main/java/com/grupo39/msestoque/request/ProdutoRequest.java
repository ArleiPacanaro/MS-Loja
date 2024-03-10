package com.grupo39.msestoque.request;

import com.grupo39.msestoque.entity.Produto;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProdutoRequest {

    @NotNull(message="Campo ID Obrigatorio")
    private Integer id;
    @NotBlank(message = "Campo Nome Obrigatorio")
    private String nome;
    @NotBlank(message = "Campo Descricao Obrigatorio")
    private String descricao;
    @NotBlank(message = "Campo Quantidade Obrigatorio")
    @PositiveOrZero(message = "Campo deve igual ou maior que zero" )
    private int quantidade_estoque;
    @NotBlank(message = "Campo Quantidade Obrigatorio")
    @PositiveOrZero(message = "Campo deve igual ou maior que zero" )
    private BigDecimal preco;


    public Produto toProduto(){

        return new Produto(
                id,
                nome,
                descricao,
                quantidade_estoque,
                preco
        );

    }

}
