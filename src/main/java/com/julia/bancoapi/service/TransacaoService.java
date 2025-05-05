package com.julia.bancoapi.service;

import com.julia.bancoapi.dto.response.TransacaoDTO;
import com.julia.bancoapi.dto.mapper.TransacaoMapper;
import com.julia.bancoapi.model.Conta;
import com.julia.bancoapi.model.Transacao;
import com.julia.bancoapi.model.TipoTransacao;
import com.julia.bancoapi.repository.TransacaoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TransacaoService {

    private final TransacaoRepository transacaoRepository;
    private final ContaService contaService;
    private final TransacaoMapper transacaoMapper;

    @Transactional
    public void transferir(Long contaOrigemId, Long contaDestinoId, BigDecimal valor) {
        if (valor.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Valor da transferência deve ser positivo.");
        }

        Conta origem = contaService.buscarConta(contaOrigemId);
        Conta destino = contaService.buscarConta(contaDestinoId);

        if (origem.getSaldo().compareTo(valor) < 0) {
            throw new RuntimeException("Saldo insuficiente na conta de origem.");
        }

        origem.setSaldo(origem.getSaldo().subtract(valor));
        Transacao debito = Transacao.builder()
                .valor(valor)
                .data(LocalDateTime.now())
                .tipo(TipoTransacao.TRANSFERENCIA_SAIDA)
                .conta(origem)
                .descricao("Transferência para conta " + destino.getNumero())
                .build();

        destino.setSaldo(destino.getSaldo().add(valor));
        Transacao credito = Transacao.builder()
                .valor(valor)
                .data(LocalDateTime.now())
                .tipo(TipoTransacao.TRANSFERENCIA_ENTRADA)
                .conta(destino)
                .descricao("Transferência recebida de " + origem.getNumero())
                .build();

        transacaoRepository.save(debito);
        transacaoRepository.save(credito);
    }

    public List<TransacaoDTO> buscarExtrato(Long contaId, LocalDateTime inicio, LocalDateTime fim) {
        List<Transacao> transacoes = transacaoRepository.findByContaIdAndDataBetween(contaId, inicio, fim);
        return transacoes.stream()
                .map(transacaoMapper::toDTO)
                .toList();
    }
}
