package br.inatel.projetoc317back.service;

import br.inatel.projetoc317back.controller.dto.TypeDto;
import br.inatel.projetoc317back.controller.form.TypeForm;
import br.inatel.projetoc317back.exception.TypeExceededPortion;
import br.inatel.projetoc317back.exception.TypeNotFoundException;
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

    private final TypeRepository typeRepository;

    @Autowired
    public TypeService(TypeRepository typeRepository) {
        this.typeRepository = typeRepository;
    }


    public TypeDto newType(TypeForm typeForm) {

        validadeNewType(typeForm.getPortion());
        Type type = new Type(typeForm);
        typeRepository.save(type);
        return TypeMapper.toTypeDto(type);
    }

    public List<TypeDto> listAllTypes() {

        List<Type> types = typeRepository.findAllByOrderByPortionDesc();
        return TypeMapper.toListTypeDto(types);
    }

    public TypeDto findTypeById(UUID id) {

        Type type = typeRepository.findById(id).orElseThrow(() -> new TypeNotFoundException(id));
        return TypeMapper.toTypeDto(type);
    }

    public Message deleteTypeById(UUID id) {

        Type type = typeRepository.findById(id).orElseThrow(() -> new TypeNotFoundException(id));
        typeRepository.deleteById(id);
        return new Message("The type " + type.getName() + " was deleted");
    }

    public TypeDto editType(UUID id, TypeForm typeForm) {

        Type type = typeRepository.findById(id)
                .map(t -> {
                    t.setName(typeForm.getName());
                    t.setPortion(typeForm.getPortion());
                    return typeRepository.save(t);
                })
                .orElseThrow(() -> new TypeNotFoundException(id));
        return TypeMapper.toTypeDto(type);
    }

    public void validadeNewType(double newPortion) {
        List<Type> types = typeRepository.findAll();
        double portionSum = types.stream().mapToDouble(Type::getPortion).sum();
        if(portionSum + newPortion > 100) throw new TypeExceededPortion(portionSum + newPortion - 100);
    }
}
