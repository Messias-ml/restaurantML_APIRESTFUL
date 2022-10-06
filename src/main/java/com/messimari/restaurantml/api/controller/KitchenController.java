package com.messimari.restaurantml.api.controller;

import com.messimari.restaurantml.domain.model.KitchenEntity;
import com.messimari.restaurantml.domain.service.RegistrationKitchenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/kitchens")
public class KitchenController {

    @Autowired
    private RegistrationKitchenService service;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public KitchenEntity registerKitchen(@RequestBody @Valid KitchenEntity kitchen){
        return service.registerKitchen(kitchen);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    public List<KitchenEntity> listKitchens(){
        return service.listKitchens();
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{id}")
    public KitchenEntity listKitchenById(@PathVariable("id") Long id){
        return service.listKitchenById(id);
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/{id}")
    public KitchenEntity updateKitchen(@PathVariable Long id,
                                       @RequestBody KitchenEntity updatedKitchen){
        return service.updateKitchen(id, updatedKitchen);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void deleteKitchen(@PathVariable("id") Long id){
        service.deleteKitchen(id);
    }
}
