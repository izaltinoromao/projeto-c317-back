package br.inatel.projetoc317back.mapper;

import br.inatel.projetoc317back.controller.dto.EntryDto;
import br.inatel.projetoc317back.model.Entry;

public class EntryMapper {

    public static EntryDto toEntryDto(Entry entry){

        EntryDto entryDto = EntryDto.builder()
                .name(entry.getName())
                .date(entry.getDate())
                .value(entry.getValue())
                .classification(entry.getType().getName())
                .description(entry.getDescription())
                .build();

        return entryDto;
    }
}
