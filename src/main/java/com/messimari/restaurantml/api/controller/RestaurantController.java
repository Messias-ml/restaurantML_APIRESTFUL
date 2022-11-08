package com.messimari.restaurantml.api.controller;

import com.messimari.restaurantml.api.model.dto.restaurant.RestaurantRequestDTO;
import com.messimari.restaurantml.api.model.dto.restaurant.RestaurantResponseDTO;
import com.messimari.restaurantml.api.model.dto.restaurant.RestaurantResponseWithAddressDTO;
import com.messimari.restaurantml.domain.model.RestaurantEntity;
import com.messimari.restaurantml.domain.service.RegistrationRestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/restaurants")
public class RestaurantController {

    @Autowired
    private RegistrationRestaurantService service;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public void createRestaurant(@RequestBody @Valid RestaurantRequestDTO restaurant){
        service.createRestaurant(restaurant);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    public List<RestaurantResponseDTO> findListRestaurants(){
        return service.findListRestaurants();
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{id}")
    public RestaurantResponseWithAddressDTO findByIdRestaurant(@PathVariable("id") Long id){
        return service.findByIdRestaurant(id);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping("/{id}")
    public void updateRestaurant(@PathVariable Long id,
                                       @RequestBody RestaurantRequestDTO updatedRestaurant){
        service.updateRestaurant(id, updatedRestaurant);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void deleteRestaurant(@PathVariable("id") Long id){
        service.deleteRestaurant(id);
    }
}
