package com.julia.bancoapi.repository;

import com.julia.bancoapi.model.Transacao;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface TransacaoRepository extends JpaRepository<Transacao, Long> {
    List<Transacao> findByContaIdAndDataBetween(Long contaId, LocalDateTime inicio, LocalDateTime fim);
}
