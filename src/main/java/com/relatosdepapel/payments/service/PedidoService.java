package com.relatosdepapel.payments.service;

import com.relatosdepapel.payments.model.Pedido;
import com.relatosdepapel.payments.repository.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;
import java.time.LocalDateTime;

@Service
public class PedidoService {

    private final PedidoRepository pedidoRepository;
    private final RestTemplate restTemplate = new RestTemplate();

    public PedidoService(PedidoRepository pedidoRepository) {
        this.pedidoRepository = pedidoRepository;
    }

    public Pedido guardarPedido(Pedido pedido) {
        Long libroId = pedido.getLibroId();

        try {
            // Validación con el microservicio de catálogo
            ResponseEntity<String> response = restTemplate.getForEntity(
                    "http://localhost:8081/api/libros/" + libroId,
                    String.class);

            if (!response.getStatusCode().is2xxSuccessful()) {
                throw new RuntimeException("Libro no válido o no disponible.");
            }

        } catch (HttpClientErrorException.NotFound e) {
            throw new RuntimeException("El libro con ID " + libroId + " no existe.");
        } catch (Exception e) {
            throw new RuntimeException("Error al validar el libro: " + e.getMessage());
        }

        // Si todo va bien, guardar el pedido
        pedido.setFechaCompra(LocalDateTime.now());
        return pedidoRepository.save(pedido);
    }

    public List<Pedido> obtenerPedidos() {
        return pedidoRepository.findAll();
    }

    public Optional<Pedido> findById(Long id) {
        return pedidoRepository.findById(id);
    }

}