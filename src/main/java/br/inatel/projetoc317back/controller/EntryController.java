package br.inatel.projetoc317back.controller;

import br.inatel.projetoc317back.controller.dto.EntryDto;
import br.inatel.projetoc317back.controller.dto.SpendDto;
import br.inatel.projetoc317back.controller.form.EntryForm;
import br.inatel.projetoc317back.model.rest.Message;
import br.inatel.projetoc317back.service.EntryService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/entry")
public class EntryController {

    private final EntryService entryService;

    @Autowired
    public EntryController(EntryService entryService) {
        this.entryService = entryService;
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public EntryDto addEntry(@RequestBody @Valid EntryForm entryForm) {

        return entryService.newEntry(entryForm);
    }

    @PutMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public EntryDto editEntry(@RequestParam @NotNull UUID id, @RequestBody @Valid EntryForm entryForm) {

        return entryService.editEntry(id, entryForm);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<EntryDto> listAllEntries() {

        return entryService.listAllEntries();
    }

    @GetMapping("/spend")
    @ResponseStatus(HttpStatus.OK)
    public List<SpendDto> listAllSpend(@RequestParam @NotNull int year, @RequestParam @NotNull int month) {

        return entryService.listAllSpend(year, month);
    }

    @GetMapping("/getone")
    @ResponseStatus(HttpStatus.OK)
    public EntryDto getEntryById(@RequestParam @NotNull UUID id) {

        return entryService.findEntryById(id);
    }

    @DeleteMapping()
    @ResponseStatus(HttpStatus.OK)
    public Message deleteEntry(@RequestParam @NotNull UUID id) {

        return entryService.deleteEntryById(id);
    }

    @GetMapping("/filter")
    @ResponseStatus(HttpStatus.OK)
    public List<EntryDto> listEntryFilter(@RequestParam @NotNull LocalDate startDate, @RequestParam @NotNull LocalDate endDate) {

        return entryService.listEntryFilter(startDate, endDate);
    }

}
