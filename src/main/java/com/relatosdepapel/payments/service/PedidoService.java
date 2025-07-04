package com.relatosdepapel.payments.service;

import com.relatosdepapel.payments.dto.ItemDTO;
import com.relatosdepapel.payments.dto.PedidoRequestDTO;
import com.relatosdepapel.payments.model.Pedido;
import com.relatosdepapel.payments.repository.PedidoRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class PedidoService {

    private final PedidoRepository pedidoRepository;
    private final RestTemplate restTemplate;

    public PedidoService(PedidoRepository pedidoRepository, RestTemplate restTemplate) {
        this.pedidoRepository = pedidoRepository;
        this.restTemplate = restTemplate;
    }

    private void validarLibroEnCatalogo(String libroId) {
        try {
            ResponseEntity<String> response = restTemplate.getForEntity(
                    "http://ms-books-catalogue/api/libros/" + libroId, String.class);

            if (!response.getStatusCode().is2xxSuccessful()) {
                throw new NoSuchElementException("Libro no válido o no disponible con ID: " + libroId);
            }

        } catch (HttpClientErrorException.NotFound e) {
            throw new NoSuchElementException("Libro no encontrado con ID: " + libroId);
        } catch (HttpClientErrorException.BadRequest e) {
            throw new IllegalArgumentException("Solicitud incorrecta al verificar el libro.");
        } catch (Exception e) {
            throw new RuntimeException("Error al validar el libro: " + e.getMessage());
        }
    }

    public List<Pedido> crearMultiplesPedidos(PedidoRequestDTO dto) {
        List<Pedido> pedidos = new ArrayList<>();

        for (ItemDTO item : dto.getItems()) {
            validarLibroEnCatalogo(item.getLibroId());

            Pedido pedido = new Pedido();
            pedido.setLibroId(item.getLibroId());
            pedido.setCantidad(item.getCantidad());
            pedido.setNombreComprador(dto.getNombreComprador());
            pedido.setEmailComprador(dto.getEmailComprador());
            pedido.setFechaCompra(LocalDateTime.now());

            pedidos.add(pedidoRepository.save(pedido));
        }

        return pedidos;
    }

    public List<Pedido> obtenerPedidos() {
        return pedidoRepository.findAll();
    }

    public Optional<Pedido> findById(Long id) {
        return pedidoRepository.findById(id);
    }
}
