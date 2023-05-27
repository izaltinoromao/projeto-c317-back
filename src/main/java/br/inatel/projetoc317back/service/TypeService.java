package br.inatel.projetoc317back.service;

import br.inatel.projetoc317back.controller.dto.TypeDto;
import br.inatel.projetoc317back.controller.form.TypeForm;
import br.inatel.projetoc317back.mapper.TypeMapper;
import br.inatel.projetoc317back.model.Type;
import br.inatel.projetoc317back.model.rest.Message;
import br.inatel.projetoc317back.repository.TypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

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

    public List<TypeDto> listAllTypes() {

        List<Type> types = typeRepository.findAll();
        List<TypeDto> typeDtos = TypeMapper.toListTypeDto(types);
        return typeDtos;
    }

    public TypeDto findTypeById(UUID id) {

        Type type = typeRepository.findById(id).orElseThrow(() -> new RuntimeException());
        TypeDto typeDto = TypeMapper.toTypeDto(type);
        return typeDto;
    }

    public Message deleteTypeById(UUID id) {

        typeRepository.deleteById(id);
        return new Message("The type was deleted");
    }

    public TypeDto editType(UUID id, TypeForm typeForm) {

        Type type = typeRepository.findById(id)
                .map(t -> {
                    t.setName(typeForm.getName());
                    t.setPortion(typeForm.getPortion());
                    return typeRepository.save(t);
                })
                .orElseThrow(() -> new RuntimeException());
        TypeDto typeDto = TypeMapper.toTypeDto(type);
        return typeDto;
    }

}
