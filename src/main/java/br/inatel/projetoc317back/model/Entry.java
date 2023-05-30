package br.inatel.projetoc317back.model;

import br.inatel.projetoc317back.controller.form.EntryForm;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Entry {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    private String name;
    private LocalDate date;
    private double value;
    @ManyToOne
    private Type type;
    private String description;

    public Entry(EntryForm entryForm, Type type) {

        this.name = entryForm.getName();
        this.date = entryForm.getDate();
        this.value = entryForm.getValue();
        this.type = type;
        this.description = entryForm.getDescription();
    }

}
