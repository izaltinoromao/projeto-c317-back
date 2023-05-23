package br.inatel.projetoc317back.service;

import br.inatel.projetoc317back.controller.dto.TypeDto;
import br.inatel.projetoc317back.controller.form.TypeForm;
import br.inatel.projetoc317back.mapper.EntryMapper;
import br.inatel.projetoc317back.mapper.TypeMapper;
import br.inatel.projetoc317back.model.Entry;
import br.inatel.projetoc317back.model.Type;
import br.inatel.projetoc317back.repository.EntryRepository;
import br.inatel.projetoc317back.repository.TypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TypeService {

    private TypeRepository typeRepository;

    @Autowired
    public TypeService(TypeRepository typeRepository) {
        this.typeRepository = typeRepository;
    }


    public TypeDto newType(TypeForm typeForm) {
        Type type = new Type(typeForm);
        typeRepository.save(type);
        return TypeMapper.toTypeDto(type);
    }
}
