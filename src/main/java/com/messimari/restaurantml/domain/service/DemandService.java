package com.messimari.restaurantml.domain.service;

import com.messimari.restaurantml.api.model.dto.demand.DemandCompleteDTO;
import com.messimari.restaurantml.api.model.dto.demand.DemandDTO;
import com.messimari.restaurantml.domain.exception.RecordNotExistRelationalException;
import com.messimari.restaurantml.domain.exception.RecordNotFoundException;
import com.messimari.restaurantml.domain.model.DemandEntity;
import com.messimari.restaurantml.domain.model.ProductEntity;
import com.messimari.restaurantml.domain.repository.DemandRepository;
import com.messimari.restaurantml.domain.repository.ProductReposiroty;
import com.messimari.restaurantml.domain.repository.RestaurantRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

import static com.messimari.restaurantml.core.ModelMapperConvert.convert;
import static com.messimari.restaurantml.core.ModelMapperConvert.convertList;

@Service
@AllArgsConstructor
public class DemandService {

    private final DemandRepository repository;

    private final RestaurantRepository restaurantRepository;

    private final ProductReposiroty productReposiroty;

    public List<DemandDTO> findDemandByIdRestaurant(Long id) {
        List<DemandEntity> allDemandByIdRestaurant = repository.findAllByIdRestaurant(id);
        return convertList(allDemandByIdRestaurant, DemandDTO.class);
    }

    public void createDemand(DemandCompleteDTO demand) {
        DemandEntity demandEntity = convert(demand, DemandEntity.class);
        Long idRestaurant = demandEntity.getRestaurant().getId();
        Long idFormPayment = demand.getFormPayment().getId();
        restaurantRepository.existsById(idRestaurant);
        restaurantRepository.existFormPayment(idRestaurant, idFormPayment)
                .orElseThrow(() -> new RecordNotExistRelationalException(new Object[]{"formPayment com id: "+ idFormPayment, "restaurant"}));
        demandEntity.getItems().forEach(item -> {
            Long idProduct = item.getProduct().getId();
            ProductEntity productEntity = productReposiroty.findById(idProduct)
                    .orElseThrow(() -> new RecordNotFoundException(new Object[]{"product com id: " + idProduct, "restaurant"}));
            item.setUnitaryPrice(productEntity.getPrice());
            BigDecimal valueTotal = productEntity.getPrice()
                    .multiply(BigDecimal.valueOf(item.getQuantity()));
            item.setTotalPrice(valueTotal.add(demandEntity.getTaxFrete()));
        });
        /*try{
            //repository.save(demandEntity);
        } catch (DataIntegrityViolationException ex) {
            if (ex.getCause().getCause().getMessage().contains("city")) {
                throw new RecordNotExistsException(new Object[]{"id city address"});
            }else if (ex.getCause().getCause().getMessage().contains("client")) {
                throw new RecordNotExistsException(new Object[]{"id client"});
            }
            throw new RecordNotExistsException(new Object[]{"id FormPayment"});
        }*/
    }

    public DemandDTO findByIdDemand(Long id) {
        return convert(repository.findById(id)
                .orElseThrow(() -> new RecordNotFoundException(new Object[]{id})),
                DemandDTO.class);
    }
}
