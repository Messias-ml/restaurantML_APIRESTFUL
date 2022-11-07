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

    private KitchenRepository kitchenRepository;

    private FormPaymentRepository formPaymentRepository;

    public RestaurantEntity createRestaurant(RestaurantRequestDTO restaurant) {
        return repository.save(getRestaurantEntity(restaurant));
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

    public RestaurantEntity updateRestaurant(Long id, RestaurantRequestDTO updatedRestaurant) {
        RestaurantEntity restaurantEntity = repository.findById(id)
                .orElseThrow(() -> new RecordNotFoundException(new Object[]{id}));
        convert(restaurantEntity, updatedRestaurant);
        //return repository.save(restaurantEntity);
        return restaurantEntity;
    }

    public void deleteRestaurant(Long id) {
        try {
            repository.delete(getRestaurantById(id));
        } catch (DataIntegrityViolationException dt) {
            throw new EntityInUseException();
        }
    }

    private RestaurantEntity getRestaurantById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RecordNotFoundException(new Object[]{id}));
    }

    private RestaurantEntity getRestaurantEntity(RestaurantRequestDTO restaurant) {
        KitchenEntity kitchenEntity = getKitchenOfRestaurant(restaurant);
        RestaurantEntity restaurantEntity = convert(restaurant, RestaurantEntity.class);
        List<IdFormPayment> idFormPayment = restaurant.getIdFormPayment();
        verifyFormPaymentAndSetInRestaurant(restaurantEntity, idFormPayment);
        restaurantEntity.setKitchen(kitchenEntity);
        return restaurantEntity;
    }

    private void verifyFormPaymentAndSetInRestaurant(RestaurantEntity restaurantEntity, List<IdFormPayment> idFormPayment) {
        if (!CollectionUtils.isEmpty(idFormPayment)) {
            List<FormPaymentEntity> formPaymentOfRestaurant = idFormPayment
                    .stream()
                    .map(fp -> formPaymentRepository.findById(fp.getId())
                            .orElseThrow(() -> new RecordNotFoundException(new Object[]{"Form Payment de id " + fp.getId()})))
                    .collect(Collectors.toList());
            restaurantEntity.setFormPayment(formPaymentOfRestaurant);
        }
    }

    private KitchenEntity getKitchenOfRestaurant(RestaurantRequestDTO restaurant) {
        Long idKitchen = restaurant.getIdKitchen();
        KitchenEntity kitchenEntity = kitchenRepository.findById(idKitchen)
                .orElseThrow(() -> new RecordNotFoundException(new Object[]{idKitchen}));
        return kitchenEntity;
    }
}
