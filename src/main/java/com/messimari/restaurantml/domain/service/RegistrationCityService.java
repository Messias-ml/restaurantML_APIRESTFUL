package com.messimari.restaurantml.domain.service;

import com.messimari.restaurantml.domain.exception.EntityInUseException;
import com.messimari.restaurantml.domain.exception.RecordNotFoundException;
import com.messimari.restaurantml.domain.model.CityEntity;
import com.messimari.restaurantml.domain.model.StateEntity;
import com.messimari.restaurantml.domain.repository.CityRepository;
import com.messimari.restaurantml.domain.repository.StateRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class RegistrationCityService {

    private CityRepository repository;

    private StateRepository stateRepository;

    public CityEntity registerCity(CityEntity restaurant) {
        setStateInCityById(restaurant);
        return repository.save(restaurant);
    }

    public List<CityEntity> listCities() {
        return repository.findAll();
    }

    public CityEntity listCityById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RecordNotFoundException(new Object[]{id}));
    }

    public CityEntity updateCity(Long id, CityEntity updatedCity) {
        CityEntity restaurantEntity = listCityById(id);
        BeanUtils.copyProperties(updatedCity, restaurantEntity, "id");
        setStateInCityById(restaurantEntity);
        return repository.save(restaurantEntity);
    }

    public void deleteCity(Long id) {
        try {
            repository.delete(listCityById(id));
        } catch (DataIntegrityViolationException dt) {
            throw new EntityInUseException();
        }
    }

    private void setStateInCityById(CityEntity restaurant) {
        Long idState = restaurant.getState().getId();
        StateEntity stateEntity = stateRepository.findById(idState)
                .orElseThrow(() -> new RecordNotFoundException(new Object[]{idState}));
        restaurant.setState(stateEntity);
    }

}
