package com.julia.bancoapi.model;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Transacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private BigDecimal valor;

    private LocalDateTime data;

    @Enumerated(EnumType.STRING)
    private TipoTransacao tipo;

    @ManyToOne
    @JoinColumn(name = "conta_id")
    private Conta conta;

    private String descricao;
}
