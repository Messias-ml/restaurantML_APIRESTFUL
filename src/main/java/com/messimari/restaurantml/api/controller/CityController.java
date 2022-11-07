package com.messimari.restaurantml.api.controller;

import com.messimari.restaurantml.api.model.dto.city.CityDTO;
import com.messimari.restaurantml.api.model.dto.city.CityRequestDTO;
import com.messimari.restaurantml.api.model.dto.city.CityResponseDTO;
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
    public void createCity(@RequestBody CityRequestDTO city){
        service.createCity(city);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    public List<CityDTO> findlistCities(){
        return service.findlistCities();
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{id}")
    public CityResponseDTO findByIdcity (@PathVariable("id") Long id){
        return service.findByIdcity(id);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping("/{id}")
    public void updateCity(@PathVariable Long id,
                                             @RequestBody CityRequestDTO updatedCity){
        service.updateCity(id, updatedCity);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void deleteCity(@PathVariable("id") Long id){
        service.deleteCity(id);
    }
}
