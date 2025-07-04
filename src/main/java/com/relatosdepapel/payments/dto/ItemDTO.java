package com.relatosdepapel.payments.dto;

import lombok.Data;

@Data
public class ItemDTO {
    private String libroId;
    private int cantidad;
    private double precioUnitario; // Opcional, si deseas validar stock o precios
}
