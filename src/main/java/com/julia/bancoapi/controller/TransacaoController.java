package com.julia.bancoapi.controller;

import com.julia.bancoapi.dto.response.TransacaoDTO;
import com.julia.bancoapi.service.TransacaoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/transacoes")
@RequiredArgsConstructor
@Tag(name = "Transações")
public class TransacaoController {

    private final TransacaoService transacaoService;

    @PostMapping("/transferencia")
    @Operation(summary = "Realizar transferência entre contas")
    public ResponseEntity<Void> transferir(@RequestParam Long origem,
                                           @RequestParam Long destino,
                                           @RequestParam BigDecimal valor) {
        transacaoService.transferir(origem, destino, valor);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/extrato")
    @Operation(summary = "Consultar extrato por conta e período")
    public ResponseEntity<List<TransacaoDTO>> extrato(
            @RequestParam Long contaId,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime inicio,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime fim) {
        return ResponseEntity.ok(transacaoService.buscarExtrato(contaId, inicio, fim));
    }
}
