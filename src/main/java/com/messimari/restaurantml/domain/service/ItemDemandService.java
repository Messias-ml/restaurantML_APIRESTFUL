package com.messimari.restaurantml.domain.service;

import com.messimari.restaurantml.api.model.dto.itemDemand.ItemDemandRequestDTO;
import com.messimari.restaurantml.domain.exception.RecordNotExistsException;
import com.messimari.restaurantml.domain.model.ItemDemandEntity;
import com.messimari.restaurantml.domain.repository.ItemDemandRepository;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import static com.messimari.restaurantml.core.ModelMapperConvert.convert;

@Service
@AllArgsConstructor
public class ItemDemandService {

    private final ItemDemandRepository repository;

    public void registerItemDemand(ItemDemandRequestDTO item) {
        try{
            ItemDemandEntity itemDemandEntity = convert(item, ItemDemandEntity.class);
            repository.save(itemDemandEntity);
        } catch (DataIntegrityViolationException ex) {
            if (ex.getCause().getCause().getMessage().contains("demand")) {
                throw new RecordNotExistsException(new Object[]{"id demand"});
            }
            throw new RecordNotExistsException(new Object[]{"id product"});
        }
    }
}
