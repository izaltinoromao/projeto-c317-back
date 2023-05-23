package br.inatel.projetoc317back.service;

import br.inatel.projetoc317back.controller.dto.EntryDto;
import br.inatel.projetoc317back.controller.form.EntryForm;
import br.inatel.projetoc317back.mapper.EntryMapper;
import br.inatel.projetoc317back.model.Entry;
import br.inatel.projetoc317back.repository.EntryRepository;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@NoArgsConstructor
public class EntryService {

    private EntryRepository entryRepository;

    @Autowired
    public EntryService(EntryRepository entryRepository) {
        this.entryRepository = entryRepository;
    }

    public EntryDto newEntry(EntryForm entryForm){

        Entry entry = new Entry(entryForm);
        entryRepository.save(entry);
        return EntryMapper.toEntryDto(entry);
    }
}
