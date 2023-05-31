package br.inatel.projetoc317back.exception;

import java.util.UUID;

public class TypeNotFoundException extends RuntimeException {

    public TypeNotFoundException(UUID id) {
        super("Type with id=" + id + " not found at Data Base.");
    }

    public TypeNotFoundException(String name) {
        super("Type with name=" + name + " not found at Data Base.");
    }
}
