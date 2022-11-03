package com.messimari.restaurantml.domain.service;

import com.messimari.restaurantml.api.model.dto.city.CityListDTO;
import com.messimari.restaurantml.api.model.dto.city.CityRequestDTO;
import com.messimari.restaurantml.api.model.dto.city.CityResponseDTO;
import com.messimari.restaurantml.domain.exception.EntityInUseException;
import com.messimari.restaurantml.domain.exception.RecordNotFoundException;
import com.messimari.restaurantml.domain.model.CityEntity;
import com.messimari.restaurantml.domain.model.StateEntity;
import com.messimari.restaurantml.domain.repository.CityRepository;
import com.messimari.restaurantml.domain.repository.StateRepository;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.messimari.restaurantml.core.ModelMapperConvert.*;

@AllArgsConstructor
@Service
public class RegistrationCityService {

    private CityRepository repository;

    private StateRepository stateRepository;

    public CityEntity registerCity(CityEntity restaurant) {
        setStateInCityById(restaurant);
        return repository.save(restaurant);
    }

    public List<CityListDTO> listCities() {
        List<CityEntity> allCities = repository.findAll();
        return convertList(allCities, CityListDTO.class);
    }

    public CityResponseDTO cityById(Long id) {
        CityEntity cityEntity = repository.findById(id)
                .orElseThrow(() -> new RecordNotFoundException(new Object[]{id}));
        return convert(cityEntity, CityResponseDTO.class);
    }

    public void updateCity(Long id, CityRequestDTO updatedCity) {
        CityEntity cityEntity = repository.findById(id)
                .orElseThrow(() -> new RecordNotFoundException(new Object[]{id}));
        cityEntity.setState(new StateEntity());
        cityEntity = convert(updatedCity, CityEntity.class);
        cityEntity.setId(id);
        repository.save(cityEntity);
    }

    public void deleteCity(Long id) {
        try {
            CityEntity cityEntity = repository.findById(id)
                    .orElseThrow(() -> new RecordNotFoundException(new Object[]{id}));
            repository.delete(cityEntity);
        } catch (DataIntegrityViolationException dt) {
            throw new EntityInUseException();
        }
    }

    private void setStateInCityById(CityEntity city) {
        Long idState = city.getState().getId();
        StateEntity stateEntity = stateRepository.findById(idState)
                .orElseThrow(() -> new RecordNotFoundException(new Object[]{idState}));
        city.setState(stateEntity);
    }

}
