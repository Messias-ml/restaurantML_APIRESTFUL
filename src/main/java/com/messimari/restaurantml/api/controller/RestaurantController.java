package com.messimari.restaurantml.api.controller;

import com.messimari.restaurantml.api.model.dto.restaurant.RestaurantRequestDTO;
import com.messimari.restaurantml.api.model.dto.restaurant.RestaurantResponseDTO;
import com.messimari.restaurantml.domain.model.RestaurantEntity;
import com.messimari.restaurantml.domain.service.RegistrationRestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/restaurants")
public class RestaurantController {

    @Autowired
    private RegistrationRestaurantService service;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public RestaurantEntity registerRestaurant(@RequestBody RestaurantRequestDTO restaurant){
        return service.registerRestaurant(restaurant);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    public List<RestaurantResponseDTO> listRestaurants(){
        return service.listRestaurants();
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{id}")
    public RestaurantResponseDTO listRestaurantById(@PathVariable("id") Long id){
        return service.getRestaurantById2(id);
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/{id}")
    public RestaurantEntity updateRestaurant(@PathVariable Long id,
                                       @RequestBody RestaurantRequestDTO updatedRestaurant){
        return service.updateRestaurant(id, updatedRestaurant);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void deleteRestaurant(@PathVariable("id") Long id){
        service.deleteRestaurant(id);
    }
}
