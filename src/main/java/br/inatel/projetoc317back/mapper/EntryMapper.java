package br.inatel.projetoc317back.mapper;

import br.inatel.projetoc317back.controller.dto.EntryDto;
import br.inatel.projetoc317back.model.Entry;

import java.util.List;
import java.util.stream.Collectors;

public class EntryMapper {

    public static EntryDto toEntryDto(Entry entry){

        EntryDto entryDto = EntryDto.builder()
                .id(entry.getId())
                .name(entry.getName())
                .date(entry.getDate())
                .value(entry.getValue())
                .classification(entry.getType().getName())
                .description(entry.getDescription())
                .build();

        return entryDto;
    }

    public static List<EntryDto> toListEntryDto(List<Entry> entries) {

        List<EntryDto> entryDtoList = entries.stream().map(e -> toEntryDto(e)).collect(Collectors.toList());

        return entryDtoList;
    }
}
