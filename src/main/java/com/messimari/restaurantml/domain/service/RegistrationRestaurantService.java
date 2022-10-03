package com.messimari.restaurantml.domain.service;

import com.messimari.restaurantml.domain.exception.EntityInUseException;
import com.messimari.restaurantml.domain.exception.RecordNotFoundException;
import com.messimari.restaurantml.domain.model.KitchenEntity;
import com.messimari.restaurantml.domain.model.RestaurantEntity;
import com.messimari.restaurantml.domain.repository.KitchenRepository;
import com.messimari.restaurantml.domain.repository.RestaurantRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class RegistrationRestaurantService {

    private RestaurantRepository repository;

    private KitchenRepository kitchenRepository;

    public RestaurantEntity registerRestaurant(RestaurantEntity restaurant) {
        setKitchenInRestaurantById(restaurant);
        return repository.save(restaurant);
    }

    public List<RestaurantEntity> listRestaurants() {
        return repository.findAll();
    }

    public RestaurantEntity listRestaurantById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RecordNotFoundException(new Object[]{id}));
    }

    public RestaurantEntity updateRestaurant(Long id, RestaurantEntity updatedRestaurant) {
        RestaurantEntity restaurantEntity = listRestaurantById(id);
        BeanUtils.copyProperties(updatedRestaurant, restaurantEntity, "id");
        setKitchenInRestaurantById(restaurantEntity);
        return repository.save(restaurantEntity);
    }

    public void deleteRestaurant(Long id) {
        try {
            repository.delete(listRestaurantById(id));
        }catch (DataIntegrityViolationException dt){
            throw new EntityInUseException();
        }
    }

    private void setKitchenInRestaurantById(RestaurantEntity restaurant) {
        Long idKitchen = restaurant.getKitchen().getId();
        KitchenEntity kitchenEntity = kitchenRepository.findById(idKitchen)
                .orElseThrow(() -> new RecordNotFoundException(new Object[]{idKitchen}));
        restaurant.setKitchen(kitchenEntity);
    }
}
