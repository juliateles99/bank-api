package com.julia.bancoapi.dto;

import com.julia.bancoapi.dto.response.TransacaoDTO;
import com.julia.bancoapi.model.Transacao;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface TransacaoMapper {
    TransacaoMapper INSTANCE = Mappers.getMapper(TransacaoMapper.class);

    TransacaoDTO toDTO(Transacao transacao);
}
