package com.messimari.restaurantml.api.controller;

import com.messimari.restaurantml.api.model.dto.kitchen.AllKitchenDTO;
import com.messimari.restaurantml.api.model.dto.kitchen.KitchenDTO;
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
    public void createKitchen(@RequestBody @Valid KitchenDTO kitchen){
        service.createKitchen(kitchen);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    public List<KitchenDTO> findListKitchens(){
        return service.findListKitchens();
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{id}")
    public AllKitchenDTO findByIdKitchen(@PathVariable("id") Long id){
        return service.findByIdKitchen(id);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping("/{id}")
    public void updateKitchen(@PathVariable Long id,
                                       @RequestBody KitchenDTO updatedKitchen){
        service.updateKitchen(id, updatedKitchen);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void deleteKitchen(@PathVariable("id") Long id){
        service.deleteKitchen(id);
    }
}
