package br.inatel.projetoc317back.model.rest;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
@AllArgsConstructor
@Builder
public class Error {

    private HttpStatus httpStatusCode;
    private String message;
}
