package br.inatel.projetoc317back.mapper;

import br.inatel.projetoc317back.controller.dto.TypeDto;
import br.inatel.projetoc317back.model.Type;

import java.util.List;
import java.util.stream.Collectors;

public class TypeMapper {

    public static TypeDto toTypeDto(Type type){

        TypeDto typeDto = TypeDto.builder()
                .id(type.getId())
                .name(type.getName())
                .portion(type.getPortion())
                .build();

        return typeDto;
    }

    public static List<TypeDto> toListTypeDto(List<Type> types) {

        List<TypeDto> typeDtos = types.stream().map(t -> toTypeDto(t)).collect(Collectors.toList());
        return typeDtos;
    }
}
