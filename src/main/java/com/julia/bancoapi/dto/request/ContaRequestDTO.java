package com.julia.bancoapi.dto.request;

import lombok.*;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ContaRequestDTO {
    private String titular;
    private String numero;
    private BigDecimal saldoInicial;
}
