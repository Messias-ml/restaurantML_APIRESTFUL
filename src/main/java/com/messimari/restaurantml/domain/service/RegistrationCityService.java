package com.messimari.restaurantml.domain.service;

import com.messimari.restaurantml.api.controller.CityController;
import com.messimari.restaurantml.api.controller.StateController;
import com.messimari.restaurantml.api.model.dto.city.CityDTO;
import com.messimari.restaurantml.api.model.dto.city.CityRequestDTO;
import com.messimari.restaurantml.api.model.dto.city.CityResponseDTO;
import com.messimari.restaurantml.domain.exception.EntityInUseException;
import com.messimari.restaurantml.domain.exception.RecordNotFoundException;
import com.messimari.restaurantml.domain.model.CityEntity;
import com.messimari.restaurantml.domain.repository.CityRepository;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.messimari.restaurantml.core.UriComplete.addUriRepresentationInResponseHeader;
import static com.messimari.restaurantml.core.modelMapper.ModelMapperConvert.convert;
import static com.messimari.restaurantml.core.modelMapper.ModelMapperConvert.convertList;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@AllArgsConstructor
@Service
public class RegistrationCityService {

    private CityRepository repository;

    public void createCity(CityRequestDTO cityRequestDTO) {
        CityEntity cityEntity = convert(cityRequestDTO, CityEntity.class);
        cityEntity.setId(null);
        CityEntity entity = repository.save(cityEntity);
        addUriRepresentationInResponseHeader(entity.getId());
    }

    public Page<CityDTO> findlistCities(Pageable pageable) {
        Page<CityEntity> allCities = repository.findAll(pageable);
        List<CityDTO> listCity = convertList(allCities.getContent(), CityDTO.class);
        return new PageImpl<>(listCity, pageable, listCity.size());
    }

    public CityResponseDTO findByIdcity(Long id) {
        CityEntity cityEntity = repository.findById(id)
                .orElseThrow(() -> new RecordNotFoundException(new Object[]{id}));
        CityResponseDTO cityDto = convert(cityEntity, CityResponseDTO.class);
        cityDto.add(linkTo(methodOn(CityController.class).findByIdcity(cityEntity.getId())).withSelfRel());
        cityDto.add(linkTo(methodOn(StateController.class).findByIdState(cityEntity.getState().getId())).withRel("stateById"));
        return cityDto;
    }

    public void updateCity(Long id, CityRequestDTO updatedCity) {
        CityEntity cityEntity = convert(updatedCity, CityEntity.class);
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
}
