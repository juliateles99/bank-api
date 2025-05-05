package com.julia.bancoapi.service;

import com.julia.bancoapi.dto.request.ContaRequestDTO;
import com.julia.bancoapi.dto.response.ContaResponseDTO;
import com.julia.bancoapi.dto.mapper.ContaMapper;
import com.julia.bancoapi.exception.ContaNaoEncontradaException;
import com.julia.bancoapi.model.Conta;
import com.julia.bancoapi.repository.ContaRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ContaService {

    private final ContaRepository contaRepository;
    private final ContaMapper contaMapper;

    public ContaResponseDTO criarConta(ContaRequestDTO dto) {
        Conta conta = contaMapper.toEntity(dto);
        conta.setSaldo(dto.getSaldoInicial() != null ? dto.getSaldoInicial() : BigDecimal.ZERO);
        return contaMapper.toResponse(contaRepository.save(conta));
    }

    public List<ContaResponseDTO> listarContas() {
        return contaRepository.findAll().stream()
                .map(contaMapper::toResponse)
                .toList();
    }

    public Conta buscarConta(Long id) {
        return contaRepository.findById(id)
                .orElseThrow(() -> new ContaNaoEncontradaException("Conta não encontrada: " + id));
    }

    @Transactional
    public void depositar(Long contaId, BigDecimal valor) {
        Conta conta = buscarConta(contaId);
        conta.setSaldo(conta.getSaldo().add(valor));
        contaRepository.save(conta);
    }

    @Transactional
    public void sacar(Long contaId, BigDecimal valor) {
        Conta conta = buscarConta(contaId);
        if (conta.getSaldo().compareTo(valor) < 0) {
            throw new RuntimeException("Saldo insuficiente.");
        }
        conta.setSaldo(conta.getSaldo().subtract(valor));
        contaRepository.save(conta);
    }
}
