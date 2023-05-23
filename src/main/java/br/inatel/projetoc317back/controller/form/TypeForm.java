package br.inatel.projetoc317back.controller.form;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TypeForm {

    @NotNull @NotEmpty
    private String name;
    @NotNull @NotEmpty
    private Double portion;
}
