package br.inatel.projetoc317back.exception;

import java.util.UUID;

public class EntryNotFoundException extends RuntimeException {

    public EntryNotFoundException (UUID id) {
        super("Entry with id=" + id + " not found at Data Base.");
    }

    public EntryNotFoundException () {
        super("Entry was not found at Data Base.");
    }

}
