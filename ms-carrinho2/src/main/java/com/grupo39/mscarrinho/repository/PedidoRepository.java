package com.grupo39.mscarrinho.repository;

import com.grupo39.mscarrinho.entity.Pedido;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface PedidoRepository extends MongoRepository<Pedido, String> {

    @Query("{ 'nomeCliente' : { $regex: ?0, $options: 'i' } }")
    List<Pedido> findByNomeCliente(String nomeCliente);
}
