package com.relatosdepapel.payments.dto;

import lombok.Data;
import java.util.List;

@Data
public class PedidoRequestDTO {
    private String nombreComprador;
    private String emailComprador;
    private List<ItemDTO> items;
}
