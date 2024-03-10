package com.grupo39.mscarrinho.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document("pedido")
public class Pedido {

    @Id
    private String id;
    private String nomeCliente;
    private List<ItemPedido> itensPedido;
    private double valorTotal;
}
