package com.messimari.restaurantml.api.controller;

import com.messimari.restaurantml.api.controller.openAPI.CityControllerOpenApi;
import com.messimari.restaurantml.api.model.dto.city.CityDTO;
import com.messimari.restaurantml.api.model.dto.city.CityRequestDTO;
import com.messimari.restaurantml.api.model.dto.city.CityResponseDTO;
import com.messimari.restaurantml.domain.service.RegistrationCityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(path = "/cities")
public class CityController implements CityControllerOpenApi {

    @Autowired
    private RegistrationCityService service;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public void createCity(@RequestBody @Valid CityRequestDTO city){
        service.createCity(city);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<CityDTO> findlistCities(){
        return service.findlistCities();
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public CityResponseDTO findByIdcity (
            @PathVariable("id") Long id){
        return service.findByIdcity(id);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping("/{id}")
    public void updateCity(
            @PathVariable Long id, @RequestBody CityRequestDTO updatedCity){
        service.updateCity(id, updatedCity);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void deleteCity(
            @PathVariable("id") Long id){
        service.deleteCity(id);
    }
}
