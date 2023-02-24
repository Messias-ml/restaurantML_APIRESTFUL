package com.messimari.restaurantml.domain.service;

import com.messimari.restaurantml.api.model.dto.demand.DemandCompleteDTO;
import com.messimari.restaurantml.api.model.dto.demand.DemandDTO;
import com.messimari.restaurantml.api.model.dto.demand.DemandToRestaurantDTO;
import com.messimari.restaurantml.domain.exception.BusinessException;
import com.messimari.restaurantml.domain.exception.RecordNotExistRelationalException;
import com.messimari.restaurantml.domain.exception.RecordNotFoundException;
import com.messimari.restaurantml.domain.model.DemandEntity;
import com.messimari.restaurantml.domain.model.ItemDemandEntity;
import com.messimari.restaurantml.domain.model.ProductEntity;
import com.messimari.restaurantml.domain.model.StatusDemand;
import com.messimari.restaurantml.domain.repository.DemandRepository;
import com.messimari.restaurantml.domain.repository.ProductReposiroty;
import com.messimari.restaurantml.domain.repository.RestaurantRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import static com.messimari.restaurantml.core.modelMapper.ModelMapperConvert.convert;
import static com.messimari.restaurantml.core.modelMapper.ModelMapperConvert.convertList;

@Service
@AllArgsConstructor
public class DemandService {

    private final DemandRepository repository;

    private final RestaurantRepository restaurantRepository;

    private final ProductReposiroty productReposiroty;

    public List<DemandToRestaurantDTO> findAllDemandByIdRestaurant(Long id) {
        List<DemandEntity> allDemandByIdRestaurant = repository.findAllByIdRestaurant(id);
        return convertList(allDemandByIdRestaurant, DemandToRestaurantDTO.class);
    }

    public void createDemand(DemandCompleteDTO demand) {
        DemandEntity demandEntity = convert(demand, DemandEntity.class);
        verifyIfExistsFormPaymentInRestaurant(demand, demandEntity);
        setValuesInItemEntityToSave(demandEntity);
        repository.save(demandEntity);
    }

    public DemandDTO findByIdDemand(Long id) {
        return convert(repository.findById(id)
                        .orElseThrow(() -> new RecordNotFoundException(new Object[]{id})),
                DemandDTO.class);
    }

    public void updateDemand(Long id, DemandCompleteDTO demand) {
        DemandEntity demandEntity = repository.findById(id)
                .orElseThrow(() -> new RecordNotFoundException(new Object[]{id}));
        convert(demandEntity, demand);
        verifyIfExistsFormPaymentInRestaurant(demand, demandEntity);
        setValuesInItemEntityToSave(demandEntity);
        repository.save(demandEntity);
    }

    private void setValuesInItemEntityToSave(DemandEntity demandEntity) {
        demandEntity.getItems().forEach(item -> {
            setUnitaryPriceIfExistIdProduct(item);
            BigDecimal valueTotal = item.getUnitaryPrice()
                    .multiply(BigDecimal.valueOf(item.getQuantity()));
            item.setTotalPrice(valueTotal);
            BigDecimal subTotalDemand = demandEntity.getSubtotal().add(valueTotal);
            demandEntity.setSubtotal(subTotalDemand);
            item.setDemand(demandEntity);
        });
        demandEntity.setTotalValue(demandEntity.getSubtotal().add(demandEntity.getTaxFrete()));
    }

    private void setUnitaryPriceIfExistIdProduct(ItemDemandEntity item) {
        Long idProduct = item.getProduct().getId();
        ProductEntity productEntity = productReposiroty.findById(idProduct)
                .orElseThrow(() -> new RecordNotFoundException(new Object[]{"product com id: " + idProduct, "restaurant"}));
        item.setUnitaryPrice(productEntity.getPrice());
    }

    private void verifyIfExistsFormPaymentInRestaurant(DemandCompleteDTO demand, DemandEntity demandEntity) {
        Long idRestaurant = demandEntity.getRestaurant().getId();
        Long idFormPayment = demand.getFormPayment().getId();
        restaurantRepository.existFormPaymentInRestaurant(idRestaurant, idFormPayment)
                .orElseThrow(() -> new RecordNotExistRelationalException(new Object[]{"formPayment com id: " + idFormPayment, "restaurant"}));
    }

    public void updateStatusDemand(Long id, StatusDemand statusDemand) {
        DemandEntity demandEntity = repository.findById(id)
                .orElseThrow(() -> new RecordNotFoundException(new Object[]{id}));
        String description = demandEntity.getStatus().getDescription();
        String message = String.format("você não pode passar do status %s para o %s", description, statusDemand.getDescription());
        if (description.equals("Cancelado") || statusDemand.getDescription().equals("Criado")) {
            throw new BusinessException(new Object[]{message});
        } else if (description.equals("Entregue") && statusDemand.getDescription().equals("Confirmado")) {
            throw new BusinessException(new Object[]{message});
        }
        if (description.equals("Confirmado")) {
            demandEntity.setConfirmationDate(LocalDateTime.now());
            repository.save(demandEntity);
        } else if (description.equals("Cancelado") && statusDemand.getDescription().equals("Confirmado")) {
            demandEntity.setCancellationDate(LocalDateTime.now());
            repository.save(demandEntity);
        } else {
            demandEntity.setStatus(statusDemand);
            repository.save(demandEntity);
        }
    }
}
