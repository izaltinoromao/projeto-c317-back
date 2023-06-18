package br.inatel.projetoc317back.service;

import br.inatel.projetoc317back.controller.dto.EntryDto;
import br.inatel.projetoc317back.controller.dto.SpendDto;
import br.inatel.projetoc317back.controller.form.EntryForm;
import br.inatel.projetoc317back.model.Entry;
import br.inatel.projetoc317back.model.Type;
import br.inatel.projetoc317back.model.rest.Message;
import br.inatel.projetoc317back.repository.EntryRepository;
import br.inatel.projetoc317back.repository.TypeRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.YearMonth;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class EntryServiceSuccessfulTests {

    @Mock
    private EntryRepository entryRepository;
    @Mock
    private TypeRepository typeRepository;
    @InjectMocks
    private EntryService entryService = new EntryService();


    Entry entry;
    Entry entry2;
    Entry entryEdit;
    List<Entry> entryList = new ArrayList<>();
    Type type;
    EntryForm entryForm;
    EntryForm entryFormEdit;
    LocalDate startOfMonth;
    LocalDate endOfMonth;

    @BeforeEach
    public void init(){
        entryForm = EntryForm.builder()
                .name("RTX 2060")
                .value(1799.25)
                .date(LocalDate.now())
                .classification("leixure")
                .description("Just a graphic card")
                .build();

        entryForm = EntryForm.builder()
                .name("RTX 2060")
                .value(1799.25)
                .date(LocalDate.now())
                .classification("leixure")
                .description("Just a graphic card")
                .build();

        type = Type.builder()
                .id(UUID.randomUUID())
                .name("leixure")
                .portion(25.5)
                .entry(entryList)
                .build();

        entry = Entry.builder()
                .id(UUID.randomUUID())
                .name("RTX 2060")
                .value(1799.25)
                .date(LocalDate.now())
                .type(type)
                .description("Just a graphic card")
                .build();

        entry2 = Entry.builder()
                .id(UUID.randomUUID())
                .name("RTX 2030")
                .value(2799.25)
                .date(LocalDate.now())
                .type(type)
                .description("Just a graphic card 2")
                .build();

        entryList.add(entry);
        entryList.add(entry2);

        entryFormEdit = EntryForm.builder()
                .name("RTX 2060 SUPER")
                .value(1699.25)
                .date(LocalDate.now())
                .classification("leixure")
                .description("Just a graphic card edited")
                .build();

        entryEdit = Entry.builder()
                .id(entry.getId())
                .name("RTX 2060 SUPER")
                .value(1699.25)
                .date(LocalDate.now())
                .type(type)
                .description("Just a graphic card edited")
                .build();

        YearMonth yearMonth = YearMonth.of(LocalDate.now().getYear(), LocalDate.now().getMonth());
        startOfMonth = yearMonth.atDay(1);
        endOfMonth = yearMonth.atEndOfMonth();
    }

    @Test
    public void givenNewEntry_whenEntryFormIsValid_shouldReturnEntryDto(){
        when(typeRepository.findByName(entryForm.getClassification())).thenReturn(type);
        when(entryRepository.save(entry)).thenReturn(entry);

        EntryDto  entryDto = entryService.newEntry(entryForm);

        assertEquals(entryForm.getName(), entryDto.getName());
        assertEquals(entryForm.getValue(), entryDto.getValue());
        assertEquals(entryForm.getDate(), entryDto.getDate());
        assertEquals(entryForm.getClassification(), entryDto.getClassification());
        assertEquals(entryForm.getDescription(), entryDto.getDescription());

    }

    @Test
    public void givenListAllEntries_whenExistEntries_shouldReturnListEntryDto(){
        when(entryRepository.findAll()).thenReturn(entryList);

        List<EntryDto>  entryDtoList = entryService.listAllEntries();

        assertEquals(entryDtoList.size(), 2);
        assertEquals(entryList.get(0).getName(), entryDtoList.get(0).getName());
        assertEquals(entryList.get(0).getValue(), entryDtoList.get(0).getValue());
        assertEquals(entryList.get(0).getDate(), entryDtoList.get(0).getDate());
        assertEquals(entryList.get(0).getType().getName(), entryDtoList.get(0).getClassification());
        assertEquals(entryList.get(0).getDescription(), entryDtoList.get(0).getDescription());

        assertEquals(entryList.get(1).getName(), entryDtoList.get(1).getName());
        assertEquals(entryList.get(1).getValue(), entryDtoList.get(1).getValue());
        assertEquals(entryList.get(1).getDate(), entryDtoList.get(1).getDate());
        assertEquals(entryList.get(1).getType().getName(), entryDtoList.get(1).getClassification());
        assertEquals(entryList.get(1).getDescription(), entryDtoList.get(1).getDescription());

    }

    @Test
    public void givenFindEntryById_whenEntryIdExists_shouldEntryDto(){
        when(entryRepository.findById(entry.getId())).thenReturn(Optional.ofNullable(entry));

        EntryDto  entryDto = entryService.findEntryById(entry.getId());

        assertEquals(entry.getId(), entryDto.getId());
        assertEquals(entry.getName(), entryDto.getName());
        assertEquals(entry.getValue(), entryDto.getValue());
        assertEquals(entry.getDate(), entryDto.getDate());
        assertEquals(entry.getType().getName(), entryDto.getClassification());
        assertEquals(entry.getDescription(), entryDto.getDescription());

    }

    @Test
    public void givenFetchType_whenExistATypeWithClassificationName_shouldReturnType(){
        when(typeRepository.findByName(entryForm.getClassification())).thenReturn(type);

        Type typeTest = entryService.fetchType(entryForm.getClassification());

        assertEquals(type.getId(), typeTest.getId());
        assertEquals(type.getName(), typeTest.getName());
        assertEquals(type.getPortion(), typeTest.getPortion());
        assertEquals(type.getEntry().size(), 2);
        assertEquals(type.getEntry().get(0), entryList.get(0));
        assertEquals(type.getEntry().get(1), entryList.get(1));

    }

    @Test
    public void givenDeleteEntryById_whenEntryIdExists_shouldReturnMessage(){
        when(entryRepository.findById(entry.getId())).thenReturn(Optional.ofNullable(entry));

        Message message = entryService.deleteEntryById(entry.getId());

        assertEquals("The entry " + entry.getName() + " was deleted", message.getMessage());

    }

    @Test
    public void givenEditEntry_whenExistsAnEntryWithIdAndEntryFormIsValid_shouldReturnEntryDto(){
        when(typeRepository.findByName(entryForm.getClassification())).thenReturn(type);
        when(entryRepository.findById(entry.getId())).thenReturn(Optional.ofNullable(entry));
        when(entryRepository.save(entry)).thenReturn(entryEdit);

        EntryDto entryDtoEdit = entryService.editEntry(entry.getId(), entryFormEdit);

        assertEquals(entryEdit.getId(), entryDtoEdit.getId());
        assertEquals(entryEdit.getName(), entryDtoEdit.getName());
        assertEquals(entryEdit.getValue(), entryDtoEdit.getValue());
        assertEquals(entryEdit.getDate(), entryDtoEdit.getDate());
        assertEquals(entryEdit.getType().getName(), entryDtoEdit.getClassification());
        assertEquals(entryEdit.getDescription(), entryDtoEdit.getDescription());

    }


    @Test
    public void givenListAllSpend_whenYearAndMonthIsValid_shouldReturnListSpendDto(){
        when(entryRepository.findByDateBetween(startOfMonth, endOfMonth)).thenReturn(entryList);

        List<SpendDto> spendDtoList = entryService.listAllSpend(LocalDate.now().getYear(), LocalDate.now().getMonth().getValue());

        assertEquals(1, spendDtoList.size());

    }




}
