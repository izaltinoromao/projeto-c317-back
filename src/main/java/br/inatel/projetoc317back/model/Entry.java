package br.inatel.projetoc317back.model;

import br.inatel.projetoc317back.controller.form.EntryForm;
import jakarta.persistence.*;
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
@Entity
public class Entry {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    private String name;
    private LocalDate date;
    private double value;
    private String classification;
    private String description;

    public Entry(EntryForm entryForm){

        this.name = entryForm.getName();
        this.date = entryForm.getDate();
        this.value = entryForm.getValue();
        this.classification = entryForm.getClassification();
        this.description = entryForm.getDescription();
    }

}
