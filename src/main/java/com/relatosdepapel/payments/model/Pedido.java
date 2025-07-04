package com.relatosdepapel.payments.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String libroId;
    private int cantidad;

    private String nombreComprador;
    private String emailComprador;

    private LocalDateTime fechaCompra;
}
