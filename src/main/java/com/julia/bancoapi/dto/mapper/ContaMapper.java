package com.julia.bancoapi.dto;

import com.julia.bancoapi.model.Conta;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ContaMapper {
    ContaMapper INSTANCE = Mappers.getMapper(ContaMapper.class);

    Conta toEntity(ContaRequestDTO dto);

    ContaResponseDTO toResponse(Conta conta);
}
