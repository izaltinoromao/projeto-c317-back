package br.inatel.projetoc317back.service;

import br.inatel.projetoc317back.controller.dto.EntryDto;
import br.inatel.projetoc317back.controller.dto.SpendDto;
import br.inatel.projetoc317back.controller.form.EntryForm;
import br.inatel.projetoc317back.exception.EntryNotFoundException;
import br.inatel.projetoc317back.exception.TypeNotFoundException;
import br.inatel.projetoc317back.mapper.EntryMapper;
import br.inatel.projetoc317back.model.Entry;
import br.inatel.projetoc317back.model.Type;
import br.inatel.projetoc317back.model.rest.Message;
import br.inatel.projetoc317back.repository.EntryRepository;
import br.inatel.projetoc317back.repository.TypeRepository;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

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
        return EntryMapper.toListEntryDto(entryList);
    }

    public EntryDto findEntryById(UUID id) {

        Entry entry = entryRepository.findById(id).orElseThrow(() -> new EntryNotFoundException(id));
        return EntryMapper.toEntryDto(entry);
    }

    public Type fetchType(String classification){

        Type originalType = typeRepository.findByName(classification);
        if (originalType == null) {throw new TypeNotFoundException(classification);}
        return originalType;
    }


    public Message deleteEntryById(UUID id) {

        Entry entry = entryRepository.findById(id).orElseThrow(() -> new EntryNotFoundException(id));
        entryRepository.deleteById(id);
        return new Message("The entry " + entry.getName() + " was deleted");
    }

    public EntryDto editEntry(UUID id, EntryForm newEntryForm) {

        Type type = fetchType(newEntryForm.getClassification());
        Entry entry = entryRepository.findById(id)
                .map(e -> {
                    e.setName(newEntryForm.getName());
                    e.setDate(newEntryForm.getDate());
                    e.setValue(newEntryForm.getValue());
                    e.setType(type);
                    e.setDescription(newEntryForm.getDescription());
                    return entryRepository.save(e);
                })
                .orElseThrow(() -> new EntryNotFoundException(id));
        return EntryMapper.toEntryDto(entry);
    }

    public List<SpendDto> listAllSpend(int year, int month) {

        YearMonth yearMonth = YearMonth.of(year, month);
        LocalDate startOfMonth = yearMonth.atDay(1);
        LocalDate endOfMonth = yearMonth.atEndOfMonth();

        List<Entry> entries = entryRepository.findByDateBetween(startOfMonth, endOfMonth);

        List<Type> types = entries.stream().map(Entry::getType).distinct().toList();
        List<SpendDto> spendList = new ArrayList<>();
        types.forEach(type -> {
            double value;
            value = entries.stream().mapToDouble(e -> {
                double entryValue = 0;
                if(e.getType() == type){
                    entryValue = e.getValue();
                }
                return entryValue;
            }).sum();
            SpendDto spendDto = new SpendDto(type.getName(), value);
            spendList.add(spendDto);
        });

        return spendList;
    }

    public List<EntryDto> listEntryFilter(LocalDate startDate, LocalDate endDate) {

        return EntryMapper.toListEntryDto(entryRepository.findByDateBetween(startDate, endDate));
    }
}
