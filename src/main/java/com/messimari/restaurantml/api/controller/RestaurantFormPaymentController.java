package com.messimari.restaurantml.api.controller;

import com.messimari.restaurantml.api.model.dto.formPayment.FormPaymentDTO;
import com.messimari.restaurantml.domain.service.RegistrationRestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/restaurants/{restaurantId}/formsPayments")
public class RestaurantFormPaymentController {

    @Autowired
    private RegistrationRestaurantService service;

    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    public List<FormPaymentDTO> findByIdFormsPaymentOfRestaurant(@PathVariable Long restaurantId){
        return service.findByIdFormsPaymentOfRestaurant(restaurantId);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping
    public void deleteFormPaymentOfRestaurant(@PathVariable Long restaurantId){
        service.deleteFormPaymentOfRestaurant(restaurantId);
    }
}
