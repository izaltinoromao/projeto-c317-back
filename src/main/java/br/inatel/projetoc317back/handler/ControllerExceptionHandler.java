package br.inatel.projetoc317back.handler;

import br.inatel.projetoc317back.exception.EntryNotFoundException;
import br.inatel.projetoc317back.exception.TypeExceededPortion;
import br.inatel.projetoc317back.exception.TypeNotFoundException;
import br.inatel.projetoc317back.model.rest.Error;
import org.hibernate.exception.JDBCConnectionException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.Objects;

@ControllerAdvice
@ResponseBody
public class ControllerExceptionHandler {

    @ExceptionHandler(JDBCConnectionException.class)
    @ResponseStatus(value = HttpStatus.SERVICE_UNAVAILABLE)
    public Error DataBaseConnectionException(JDBCConnectionException jdbcConnectionException){
        return Error.builder()
                .httpStatusCode(HttpStatus.SERVICE_UNAVAILABLE)
                .message(jdbcConnectionException.getMessage())
                .build();
    }

    @ExceptionHandler(TypeNotFoundException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public Error TypeNotFoundException(TypeNotFoundException typeNotFoundException){
        return Error.builder()
                .httpStatusCode(HttpStatus.NOT_FOUND)
                .message(typeNotFoundException.getMessage())
                .build();
    }

    @ExceptionHandler(EntryNotFoundException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public Error EntryNotFoundException(EntryNotFoundException entryNotFoundException){
        return Error.builder()
                .httpStatusCode(HttpStatus.NOT_FOUND)
                .message(entryNotFoundException.getMessage())
                .build();
    }

    @ExceptionHandler(TypeExceededPortion.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public Error TypeExceededPortion(TypeExceededPortion typeExceededPortion){
        return Error.builder()
                .httpStatusCode(HttpStatus.BAD_REQUEST)
                .message(typeExceededPortion.getMessage())
                .build();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public Error methodArgumentNotValidException(MethodArgumentNotValidException methodArgumentNotValidException){
        return Error.builder()
                .httpStatusCode(HttpStatus.BAD_REQUEST)
                .message(Objects.requireNonNull(methodArgumentNotValidException.getFieldError()).getField() + " must not be empty or null and not negative for value")
                .build();
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public Error missingServletRequestParameterException(MissingServletRequestParameterException missingServletRequestParameterException){
        return Error.builder()
                .httpStatusCode(HttpStatus.BAD_REQUEST)
                .message(missingServletRequestParameterException.getParameterName() + " parameter is required").build();
    }
}
