package com.relatosdepapel.payments.service;

import com.relatosdepapel.payments.model.Pedido;
import com.relatosdepapel.payments.repository.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.time.LocalDateTime;

@Service
public class PedidoService {

    private final PedidoRepository pedidoRepository;
    private final RestTemplate restTemplate;

    public PedidoService(PedidoRepository pedidoRepository, RestTemplate restTemplate) {
        this.pedidoRepository = pedidoRepository;
        this.restTemplate = restTemplate;
    }

    /**
     * Realiza una llamada al microservicio de catálogo para verificar si el libro existe.
     * Si no existe, lanza una excepción adecuada.
     */
    private void validarLibroEnCatalogo(Long libroId) {
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


    public Pedido guardarPedido(Pedido pedido) {
        Long libroId = pedido.getLibroId();
        validarLibroEnCatalogo(libroId);

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