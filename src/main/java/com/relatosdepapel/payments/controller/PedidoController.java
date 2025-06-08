package com.relatosdepapel.payments.controller;

import com.relatosdepapel.payments.model.Pedido;
import com.relatosdepapel.payments.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/pedidos")
public class PedidoController {

    @Autowired
    private PedidoService pedidoService;

    @PostMapping
    public Pedido crearPedido(@RequestBody Pedido pedido) {
        return pedidoService.guardarPedido(pedido);
    }

    @GetMapping
    public List<Pedido> obtenerTodos() {
        return pedidoService.obtenerPedidos();
    }

}
