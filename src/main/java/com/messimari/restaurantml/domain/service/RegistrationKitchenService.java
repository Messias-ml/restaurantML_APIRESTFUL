package com.messimari.restaurantml.domain.service;

import com.messimari.restaurantml.api.model.dto.kitchen.AllKitchenDTO;
import com.messimari.restaurantml.api.model.dto.kitchen.KitchenDTO;
import com.messimari.restaurantml.core.modelMapper.ModelMapperConvert;
import com.messimari.restaurantml.domain.exception.EntityInUseException;
import com.messimari.restaurantml.domain.exception.RecordNotFoundException;
import com.messimari.restaurantml.domain.model.KitchenEntity;
import com.messimari.restaurantml.domain.model.assemble.KitchenModelAssemble;
import com.messimari.restaurantml.domain.repository.KitchenRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.PagedModel;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.messimari.restaurantml.core.modelMapper.ModelMapperConvert.convert;
import static com.messimari.restaurantml.core.modelMapper.ModelMapperConvert.convertList;

@AllArgsConstructor
@Service
public class RegistrationKitchenService {

    private KitchenRepository repository;

    @Autowired
    private PagedResourcesAssembler<KitchenEntity> pagedResourcesAssembler;
    @Autowired
    private KitchenModelAssemble kitchenModelAssemble;

    public void createKitchen(KitchenDTO kitchenDTO) {
        repository.save(convert(kitchenDTO, KitchenEntity.class));
    }

    public PagedModel<KitchenDTO> findListKitchens(Pageable page) {
        Page<KitchenEntity> kitchenEntities = repository.findAll(page);
        return pagedResourcesAssembler.toModel(kitchenEntities, kitchenModelAssemble);
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
