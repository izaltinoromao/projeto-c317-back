package br.inatel.projetoc317back.mapper;

import br.inatel.projetoc317back.controller.dto.EntryDto;
import br.inatel.projetoc317back.controller.dto.TypeDto;
import br.inatel.projetoc317back.model.Entry;
import br.inatel.projetoc317back.model.Type;

public class TypeMapper {

    public static TypeDto toTypeDto(Type type){

        TypeDto typeDto = TypeDto.builder()
                .name(type.getName())
                .portion(type.getPortion())
                .build();

        return typeDto;
    }
}
