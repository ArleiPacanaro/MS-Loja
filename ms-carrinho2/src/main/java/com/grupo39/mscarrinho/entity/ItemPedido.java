package com.grupo39.mscarrinho.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ItemPedido {

    private Integer idProduto;
    private int quantidade;
}