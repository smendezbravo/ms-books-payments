package com.relatosdepapel.payments.controller;

import com.relatosdepapel.payments.dto.PedidoRequestDTO;
import com.relatosdepapel.payments.model.Pedido;
import com.relatosdepapel.payments.service.PedidoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pedidos")
//@CrossOrigin(origins = "*")
public class PedidoController {

    private final PedidoService pedidoService;

    public PedidoController(PedidoService pedidoService) {
        this.pedidoService = pedidoService;
    }

    @PostMapping
    public ResponseEntity<List<Pedido>> crearPedido(@RequestBody PedidoRequestDTO dto) {
        List<Pedido> pedidos = pedidoService.crearMultiplesPedidos(dto);
        return ResponseEntity.ok(pedidos);
    }

    @GetMapping
    public List<Pedido> listarPedidos() {
        return pedidoService.obtenerPedidos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pedido> buscarPorId(@PathVariable Long id) {
        return pedidoService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
