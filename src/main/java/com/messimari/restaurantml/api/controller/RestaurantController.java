package com.messimari.restaurantml.api.controller;

import com.messimari.restaurantml.api.model.dto.restaurant.RestaurantRequestDTO;
import com.messimari.restaurantml.api.model.dto.restaurant.RestaurantResponseDTO;
import com.messimari.restaurantml.api.model.dto.restaurant.RestaurantResponseWithAddressDTO;
import com.messimari.restaurantml.api.model.dto.user.UserBasicDTO;
import com.messimari.restaurantml.api.model.dto.user.UserOwnerIdDTO;
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
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/{id}/owners")
    public void associateOwnersWithRestaurant(@RequestBody @Valid UserOwnerIdDTO owners, @PathVariable Long id){
        service.associateOwnersWithRestaurant(owners, id);
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

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{id}/owners")
    public List<UserBasicDTO> findByIdOwnerOfRestaurant(@PathVariable("id") Long id){
        return service.findByIdOwnersOfRestaurant(id);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping("/{id}")
    public void updateRestaurant(@PathVariable Long id,
                                       @RequestBody RestaurantRequestDTO updatedRestaurant){
        service.updateRestaurant(id, updatedRestaurant);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PatchMapping("/{id}/owners")
    public void updateOwnersOfRestaurant(@PathVariable Long id,
                                         @RequestBody UserOwnerIdDTO owners){
        service.updateOwnersOfRestaurant(id, owners);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void deleteRestaurant(@PathVariable("id") Long id){
        service.deleteRestaurant(id);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}/owners")
    public void disassociateOwnersOfRestaurant(@PathVariable("id") Long id){
        service.disassociateOwnersOfRestaurant(id);
    }
}
