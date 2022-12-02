package com.messimari.restaurantml.domain.service;

import com.messimari.restaurantml.api.model.dto.formPayment.FormPaymentDTO;
import com.messimari.restaurantml.api.model.dto.restaurant.RestaurantRequestDTO;
import com.messimari.restaurantml.api.model.dto.restaurant.RestaurantResponseDTO;
import com.messimari.restaurantml.api.model.dto.restaurant.RestaurantResponseWithAddressDTO;
import com.messimari.restaurantml.api.model.dto.user.UserBasicDTO;
import com.messimari.restaurantml.api.model.dto.user.UserOwnerIdDTO;
import com.messimari.restaurantml.domain.exception.EntityInUseException;
import com.messimari.restaurantml.domain.exception.RecordNotExistsException;
import com.messimari.restaurantml.domain.exception.RecordNotFoundException;
import com.messimari.restaurantml.domain.model.KitchenEntity;
import com.messimari.restaurantml.domain.model.RestaurantEntity;
import com.messimari.restaurantml.domain.repository.RestaurantRepository;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.orm.jpa.JpaObjectRetrievalFailureException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static com.messimari.restaurantml.core.ModelMapperConvert.convert;
import static com.messimari.restaurantml.core.ModelMapperConvert.convertList;

@Service
@AllArgsConstructor
public class RegistrationRestaurantService {

    private RestaurantRepository repository;

    public void createRestaurant(RestaurantRequestDTO restaurant) {
        RestaurantEntity restaurantEntity = convert(restaurant, RestaurantEntity.class);
        restaurantEntity.setId(null);
        try {
            repository.save(restaurantEntity);
        } catch (DataIntegrityViolationException ex) {
            if (ex.getCause().getCause().getMessage().contains("form_payment")) {
                throw new RecordNotExistsException(new Object[]{"id FormPayment"});
            }
            throw new RecordNotExistsException(new Object[]{"id owner"});
        }
    }

    public void associateOwnersWithRestaurant(UserOwnerIdDTO owners, Long id) {
        RestaurantEntity restaurantEntity = repository.findById(id)
                .orElseThrow(() -> new RecordNotFoundException(new Object[]{id}));
        convert(owners, restaurantEntity);
        try {
            repository.save(restaurantEntity);
        } catch (JpaObjectRetrievalFailureException ex) {
            throw new RecordNotExistsException(new Object[]{"id owner"});
        }
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

    public List<FormPaymentDTO> findByIdFormsPaymentOfRestaurant(Long id) {
        RestaurantEntity restaurantEntity = repository.findById(id)
                .orElseThrow(() -> new RecordNotFoundException(new Object[]{id}));
        return convertList(restaurantEntity.getFormPayment(), FormPaymentDTO.class);
    }

    public void updateRestaurant(Long id, RestaurantRequestDTO updatedRestaurant) {
        RestaurantEntity restaurantEntity = repository.findById(id)
                .orElseThrow(() -> new RecordNotFoundException(new Object[]{id}));
        restaurantEntity.setKitchen(new KitchenEntity());
        restaurantEntity.setFormPayment(List.of());
        restaurantEntity.setOwner(List.of());
        convert(updatedRestaurant, restaurantEntity);
        restaurantEntity.setId(id);
        try {
            repository.save(restaurantEntity);
        } catch (JpaObjectRetrievalFailureException ex) {
            if (ex.getMessage().contains("FormPaymentEntity")) {
                throw new RecordNotExistsException(new Object[]{"id FormPayment"});
            } else {
                throw new RecordNotExistsException(new Object[]{"id owner"});
            }
        }
    }

    public void deleteRestaurant(Long id) {
        try {
            repository.deleteById(id);
        } catch (DataIntegrityViolationException dt) {
            throw new EntityInUseException();
        } catch (EmptyResultDataAccessException empty) {
            throw new RecordNotFoundException(new Object[]{id});
        }
    }

    public void deleteFormPaymentOfRestaurant(Long id) {
        RestaurantEntity restaurantEntity = repository.findById(id)
                .orElseThrow(() -> new RecordNotFoundException(new Object[]{id}));
        restaurantEntity.setFormPayment(new ArrayList<>());
        repository.save(restaurantEntity);
    }

    public List<UserBasicDTO> findByIdOwnersOfRestaurant(Long id) {
        RestaurantEntity restaurantEntity = repository.findByIdWithOwner(id)
                .orElseThrow(() -> new RecordNotFoundException(new Object[]{id}));
        return convertList(restaurantEntity.getOwner(), UserBasicDTO.class);
    }

    public void updateOwnersOfRestaurant(Long id, UserOwnerIdDTO owners) {
        RestaurantEntity restaurantEntity = repository.findById(id)
                .orElseThrow(() -> new RecordNotFoundException(new Object[]{id}));
        restaurantEntity.setOwner(List.of());
        try {
            repository.save(convert(owners, restaurantEntity));
        } catch (JpaObjectRetrievalFailureException ex) {
            throw new RecordNotExistsException(new Object[]{"id owner"});
        }
    }

    public void disassociateOwnersOfRestaurant(Long id) {
        repository.deleteOwners(id);
    }
}
