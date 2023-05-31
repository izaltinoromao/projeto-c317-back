package br.inatel.projetoc317back.controller.form;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FilterDateForm {

    @NotNull
    private LocalDate startDate;
    @NotNull
    private LocalDate endDate;
}
