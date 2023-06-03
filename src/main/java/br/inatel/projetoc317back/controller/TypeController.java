package br.inatel.projetoc317back.controller;

import br.inatel.projetoc317back.controller.dto.TypeDto;
import br.inatel.projetoc317back.controller.form.TypeForm;
import br.inatel.projetoc317back.model.rest.Message;
import br.inatel.projetoc317back.service.TypeService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/type")
public class TypeController {

    private final TypeService typeService;

    @Autowired
    public TypeController(TypeService typeService) {
        this.typeService = typeService;
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public TypeDto addType(@RequestBody @Valid TypeForm typeForm) {

        return typeService.newType(typeForm);
    }

    @PutMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public TypeDto editType(@RequestParam @NotNull UUID id, @RequestBody @Valid TypeForm typeForm) {

        return typeService.editType(id, typeForm);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<TypeDto> listAllTypes() {

        return typeService.listAllTypes();
    }

    @GetMapping("/getone")
    @ResponseStatus(HttpStatus.OK)
    public TypeDto getTypeById(@RequestParam @NotNull UUID id) {

        return typeService.findTypeById(id);
    }

    @DeleteMapping()
    @ResponseStatus(HttpStatus.OK)
    public Message deleteType(@RequestParam @NotNull UUID id) {

        return typeService.deleteTypeById(id);
    }

}
