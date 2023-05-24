package br.inatel.projetoc317back.service;

import br.inatel.projetoc317back.controller.dto.EntryDto;
import br.inatel.projetoc317back.controller.form.EntryForm;
import br.inatel.projetoc317back.mapper.EntryMapper;
import br.inatel.projetoc317back.model.Entry;
import br.inatel.projetoc317back.model.Type;
import br.inatel.projetoc317back.repository.EntryRepository;
import br.inatel.projetoc317back.repository.TypeRepository;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@NoArgsConstructor
public class EntryService {

    private EntryRepository entryRepository;
    private TypeRepository typeRepository;

    @Autowired
    public EntryService(EntryRepository entryRepository, TypeRepository typeRepository) {
        this.entryRepository = entryRepository;
        this.typeRepository = typeRepository;
    }

    public EntryDto newEntry(EntryForm entryForm){

        Type type = fetchType(entryForm.getClassification());
        Entry entry = new Entry(entryForm, type);
        entryRepository.save(entry);
        return EntryMapper.toEntryDto(entry);
    }

    public List<EntryDto> listAllEntries() {

        List<Entry> entryList = entryRepository.findAll();
        List<EntryDto> entryDtoList = EntryMapper.toListEntryDto(entryList);
        return entryDtoList;
    }

    public Type fetchType(String classification){

        Type originalType = typeRepository.findByName(classification);
        return originalType;
    }


}
