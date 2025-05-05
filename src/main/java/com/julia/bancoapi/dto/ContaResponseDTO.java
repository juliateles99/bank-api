package com.julia.bancoapi.dto;

import lombok.*;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ContaResponseDTO {
    private Long id;
    private String titular;
    private String numero;
    private BigDecimal saldo;
}
