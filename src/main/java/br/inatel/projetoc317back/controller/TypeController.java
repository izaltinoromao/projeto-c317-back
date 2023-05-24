package br.inatel.projetoc317back.controller;

import br.inatel.projetoc317back.controller.dto.TypeDto;
import br.inatel.projetoc317back.controller.form.TypeForm;
import br.inatel.projetoc317back.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/type")
public class TypeController {

    private TypeService typeService;

    @Autowired
    public TypeController(TypeService typeService) {
        this.typeService = typeService;
    }

    @PostMapping("/new")
    @ResponseStatus(HttpStatus.CREATED)
    public TypeDto addType(@RequestBody TypeForm typeForm) {

        return typeService.newType(typeForm);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<TypeDto> listAllTypes() {

        return typeService.listAllTypes();
    }

}
