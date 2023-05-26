package br.inatel.projetoc317back.controller;

import br.inatel.projetoc317back.controller.dto.EntryDto;
import br.inatel.projetoc317back.controller.form.EntryForm;
import br.inatel.projetoc317back.model.rest.Message;
import br.inatel.projetoc317back.service.EntryService;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/entry")
public class EntryController {

    private EntryService entryService;

    @Autowired
    public EntryController(EntryService entryService) {
        this.entryService = entryService;
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public EntryDto addEntry(@RequestBody EntryForm entryForm) {

        return entryService.newEntry(entryForm);
    }

    @PutMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public EntryDto editEntry(@RequestParam @NotNull UUID id, @RequestBody EntryForm entryForm) {

        return entryService.editEntry(id, entryForm);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<EntryDto> listAllEntries() {

        return entryService.listAllEntries();
    }

    @GetMapping("/getone")
    @ResponseStatus(HttpStatus.OK)
    public EntryDto getEntryById(@RequestParam @NotNull UUID id) {

        return entryService.findEntryById(id);
    }

    @DeleteMapping()
    @ResponseStatus(HttpStatus.OK)
    public Message deleteEntry(@RequestParam @NotNull UUID id){

        return entryService.deleteEntryById(id);
    }

}
