package com.messimari.restaurantml.domain.service;

import com.messimari.restaurantml.api.model.dto.kitchen.AllKitchenDTO;
import com.messimari.restaurantml.api.model.dto.kitchen.KitchenDTO;
import com.messimari.restaurantml.core.ModelMapperConfig;
import com.messimari.restaurantml.domain.exception.EntityInUseException;
import com.messimari.restaurantml.domain.exception.RecordNotFoundException;
import com.messimari.restaurantml.domain.model.KitchenEntity;
import com.messimari.restaurantml.domain.repository.KitchenRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.messimari.restaurantml.core.ModelMapperConfig.modelMapper;
import static com.messimari.restaurantml.core.ModelMapperConvert.convert;
import static com.messimari.restaurantml.core.ModelMapperConvert.convertList;

@AllArgsConstructor
@Service
public class RegistrationKitchenService {

    private KitchenRepository repository;

    public void createKitchen(KitchenDTO kitchenDTO) {
        repository.save(convert(kitchenDTO, KitchenEntity.class));
    }

    public List<KitchenDTO> findListKitchens() {
        return convertList(repository.findAll(), KitchenDTO.class);
    }

    public AllKitchenDTO findByIdKitchen(Long id) {
        KitchenEntity kitchenEntity = findById(id);
        return convert(kitchenEntity, AllKitchenDTO.class);
    }

    public void updateKitchen(Long id, KitchenDTO updatedKitchen) {
        KitchenEntity kitchenEntity = findById(id);
        kitchenEntity = convert(updatedKitchen, kitchenEntity);
        repository.save(kitchenEntity);
    }

    public void deleteKitchen(Long id) {
        try {
            repository.deleteById(id);
        }catch (DataIntegrityViolationException dt){
            throw new EntityInUseException();
        }
    }

    private KitchenEntity findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RecordNotFoundException(new Object[]{id}));
    }

}
