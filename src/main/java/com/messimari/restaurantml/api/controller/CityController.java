package com.messimari.restaurantml.api.controller;

import com.messimari.restaurantml.domain.model.CityEntity;
import com.messimari.restaurantml.domain.service.RegistrationCityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cities")
public class CityController {

    @Autowired
    private RegistrationCityService service;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public CityEntity registerRestaurant(@RequestBody CityEntity restaurant){
        return service.registerCity(restaurant);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    public List<CityEntity> listCities(){
        return service.listCities();
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{id}")
    public CityEntity listCityById(@PathVariable("id") Long id){
        return service.listCityById(id);
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/{id}")
    public CityEntity updateCity(@PathVariable Long id,
                                             @RequestBody CityEntity updatedCity){
        return service.updateCity(id, updatedCity);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void deleteCity(@PathVariable("id") Long id){
        service.deleteCity(id);
    }
}