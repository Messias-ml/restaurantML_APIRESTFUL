package com.messimari.restaurantml.domain.service;

import com.messimari.restaurantml.api.model.dto.restaurant.IdFormPayment;
import com.messimari.restaurantml.api.model.dto.restaurant.RestaurantRequestDTO;
import com.messimari.restaurantml.api.model.dto.restaurant.RestaurantResponseDTO;
import com.messimari.restaurantml.api.model.dto.restaurant.RestaurantResponseWithAddressDTO;
import com.messimari.restaurantml.domain.exception.EntityInUseException;
import com.messimari.restaurantml.domain.exception.RecordNotFoundException;
import com.messimari.restaurantml.domain.model.FormPaymentEntity;
import com.messimari.restaurantml.domain.model.KitchenEntity;
import com.messimari.restaurantml.domain.model.RestaurantEntity;
import com.messimari.restaurantml.domain.repository.FormPaymentRepository;
import com.messimari.restaurantml.domain.repository.KitchenRepository;
import com.messimari.restaurantml.domain.repository.RestaurantRepository;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.stream.Collectors;

import static com.messimari.restaurantml.core.ModelMapperConvert.convert;
import static com.messimari.restaurantml.core.ModelMapperConvert.convertList;

@Service
@AllArgsConstructor
public class RegistrationRestaurantService {

    private RestaurantRepository repository;

    public void createRestaurant(RestaurantRequestDTO restaurant) {
        RestaurantEntity restaurantEntity = convert(restaurant, RestaurantEntity.class);
        restaurantEntity.setId(null);
        repository.save(restaurantEntity);
    }

    public List<RestaurantResponseDTO> findListRestaurants() {
        List<RestaurantEntity> allRestaurants = repository.findAll();
        return convertList(allRestaurants, RestaurantResponseDTO.class);
    }

    public RestaurantResponseWithAddressDTO findByIdRestaurant(Long id) {
        RestaurantEntity restaurantEntity = repository.findById(id)
                .orElseThrow(() -> new RecordNotFoundException(new Object[]{id}));
        return convert(restaurantEntity, RestaurantResponseWithAddressDTO.class);
    }

    public void updateRestaurant(Long id, RestaurantRequestDTO updatedRestaurant) {
        RestaurantEntity restaurantEntity = repository.findById(id)
                .orElseThrow(() -> new RecordNotFoundException(new Object[]{id}));
        restaurantEntity.setKitchen(new KitchenEntity());
        convert(updatedRestaurant, restaurantEntity);
        restaurantEntity.setId(id);
        repository.save(restaurantEntity);
    }

    public void deleteRestaurant(Long id) {
        try {
            repository.deleteById(id);
        } catch (DataIntegrityViolationException dt) {
            throw new EntityInUseException();
        }catch (EmptyResultDataAccessException empty){
            throw new RecordNotFoundException(new Object[]{id});
        }
    }
}
