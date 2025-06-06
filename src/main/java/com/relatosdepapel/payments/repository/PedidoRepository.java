package com.relatosdepapel.payments.repository;

import com.relatosdepapel.payments.model.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {
}
