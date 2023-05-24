package br.inatel.projetoc317back.controller;

import br.inatel.projetoc317back.controller.dto.EntryDto;
import br.inatel.projetoc317back.controller.form.EntryForm;
import br.inatel.projetoc317back.model.Entry;
import br.inatel.projetoc317back.repository.EntryRepository;
import br.inatel.projetoc317back.service.EntryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/entry")
public class EntryController {

    private EntryService entryService;

    @Autowired
    public EntryController(EntryService entryService) {
        this.entryService = entryService;
    }

    @PostMapping("/new")
    @ResponseStatus(HttpStatus.CREATED)
    public EntryDto addEntry(@RequestBody EntryForm entryForm) {

        return entryService.newEntry(entryForm);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<EntryDto> listAllDrags() {

        return entryService.listAllEntries();
    }

}
