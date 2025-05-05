package com.julia.bancoapi.dto;

import com.julia.bancoapi.model.TipoTransacao;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TransacaoDTO {
    private Long id;
    private BigDecimal valor;
    private LocalDateTime data;
    private TipoTransacao tipo;
    private String descricao;
}
