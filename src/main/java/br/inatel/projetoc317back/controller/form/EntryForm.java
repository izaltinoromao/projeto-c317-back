package br.inatel.projetoc317back.controller.form;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.*;

import java.time.LocalDate;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EntryForm {

    @NotNull @NotEmpty
    private String name;
    @NotNull
    private LocalDate date;
    @NotNull @Positive
    private double value;
    @NotNull @NotEmpty
    private String classification;
    private String description;
}
