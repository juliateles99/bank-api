package com.julia.bancoapi.controller;

import com.julia.bancoapi.dto.request.ContaRequestDTO;
import com.julia.bancoapi.dto.response.ContaResponseDTO;
import com.julia.bancoapi.service.ContaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/contas")
@RequiredArgsConstructor
@Tag(name = "Contas")
public class ContaController {

    private final ContaService contaService;

    @PostMapping
    @Operation(summary = "Criar nova conta")
    public ResponseEntity<ContaResponseDTO> criar(@RequestBody @Valid ContaRequestDTO dto) {
        return ResponseEntity.ok(contaService.criarConta(dto));
    }

    @GetMapping
    @Operation(summary = "Listar todas as contas")
    public ResponseEntity<List<ContaResponseDTO>> listar() {
        return ResponseEntity.ok(contaService.listarContas());
    }

    @PostMapping("/{id}/deposito")
    @Operation(summary = "Depositar valor em uma conta")
    public ResponseEntity<Void> depositar(@PathVariable Long id, @RequestParam BigDecimal valor) {
        contaService.depositar(id, valor);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/{id}/saque")
    @Operation(summary = "Sacar valor de uma conta")
    public ResponseEntity<Void> sacar(@PathVariable Long id, @RequestParam BigDecimal valor) {
        contaService.sacar(id, valor);
        return ResponseEntity.ok().build();
    }
}
