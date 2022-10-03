package com.messimari.restaurantml.domain.service;

import com.messimari.restaurantml.domain.exception.EntityInUseException;
import com.messimari.restaurantml.domain.exception.RecordNotFoundException;
import com.messimari.restaurantml.domain.model.KitchenEntity;
import com.messimari.restaurantml.domain.repository.KitchenRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class RegistrationKitchenService {

    private KitchenRepository repository;

    public KitchenEntity registerKitchen(KitchenEntity kitchen) {
        return repository.save(kitchen);
    }

    public List<KitchenEntity> listKitchens() {
        return repository.findAll();
    }

    public KitchenEntity listKitchenById(Long id) {
    return repository.findById(id)
            .orElseThrow(() -> new RecordNotFoundException(new Object[]{id}));
    }

    public KitchenEntity updateKitchen(Long id, KitchenEntity updatedKitchen) {
        KitchenEntity kitchenEntity = listKitchenById(id);
        BeanUtils.copyProperties(updatedKitchen, kitchenEntity, "id");
        return repository.save(kitchenEntity);
    }

    public void deleteKitchen(Long id) {
        try {
            repository.delete(listKitchenById(id));
        }catch (DataIntegrityViolationException dt){
            throw new EntityInUseException();
        }
    }
}
