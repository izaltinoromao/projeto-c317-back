package br.inatel.projetoc317back.model;

import br.inatel.projetoc317back.controller.form.EntryForm;
import br.inatel.projetoc317back.controller.form.TypeForm;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Type {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    private String name;
    private Double portion;
    @OneToMany(mappedBy = "type")
    private List<Entry> entry;

    public Type(TypeForm typeForm){

        this.name = typeForm.getName();
        this.portion = typeForm.getPortion();
    }
}
