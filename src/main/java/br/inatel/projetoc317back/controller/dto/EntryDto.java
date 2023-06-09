package br.inatel.projetoc317back.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EntryDto {

    private UUID id;
    private String name;
    private LocalDate date;
    private double value;
    private String classification;
    private String description;
}
