package br.inatel.projetoc317back.controller.form;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EntryForm {

    @NotNull @NotEmpty
    private String name;
    @NotNull @NotEmpty
    private LocalDate date;
    @NotNull @NotEmpty
    private double value;
    @NotNull @NotEmpty
    private String classification;
    private String description;
}
