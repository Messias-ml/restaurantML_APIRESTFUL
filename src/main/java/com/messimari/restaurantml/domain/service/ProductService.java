package com.messimari.restaurantml.domain.service;

import com.messimari.restaurantml.api.model.dto.product.ProductCompleteDTO;
import com.messimari.restaurantml.api.model.dto.product.ProductRequestDTO;
import com.messimari.restaurantml.api.model.dto.product.ProductResponseDTO;
import com.messimari.restaurantml.domain.exception.RecordNotExistsException;
import com.messimari.restaurantml.domain.exception.RecordNotFoundException;
import com.messimari.restaurantml.domain.model.ProductEntity;
import com.messimari.restaurantml.domain.repository.ProductReposiroty;
import com.messimari.restaurantml.domain.repository.RestaurantRepository;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.messimari.restaurantml.core.ModelMapperConvert.convert;
import static com.messimari.restaurantml.core.ModelMapperConvert.convertList;

@Service
@AllArgsConstructor
public class ProductService {

    private final RestaurantRepository restaurantRepository;

    private final ProductReposiroty productReposiroty;

    public void createProductToRestaurant(ProductRequestDTO product) {
        try{
            ProductEntity productEntity = convert(product, ProductEntity.class);
            productEntity.setId(null);
            productReposiroty.save(productEntity);
        }catch (DataIntegrityViolationException dt){
            throw new RecordNotFoundException(new Object[]{"de id "+product.getIdRestaurant() + " do restaurant"});
        }
    }

    public List<ProductResponseDTO> findProductsOfRestaurant(Long idRestaurant) {
        List<ProductEntity> allProductByRestaurant = productReposiroty.findAllByIdRestaurant(idRestaurant);
        return convertList(allProductByRestaurant, ProductResponseDTO.class);
    }

    public ProductCompleteDTO findProductById(Long id) {
        ProductEntity productEntity = productReposiroty.findById(id)
                .orElseThrow(() -> new RecordNotFoundException(new Object[]{id}));
        return convert(productEntity, ProductCompleteDTO.class);
    }

    public ProductCompleteDTO findByIdProductOfRestaurant(Long idRestaurant, Long id) {
        ProductEntity productEntity = productReposiroty.findById(id)
                .orElseThrow(() -> new RecordNotFoundException(new Object[]{id}));
        Long productIdRestaurant = productEntity.getRestaurant().getId();
        if (!productIdRestaurant.equals(idRestaurant)){
            throw new RecordNotFoundException(new Object[]{"de restaurant com o id "+idRestaurant+" não faz parte deste produto, portanto"});
        }
        return convert(productEntity, ProductCompleteDTO.class);
    }

    public void updateProductOfRestaurantById(Long id, ProductRequestDTO product) {
        try{
            ProductEntity productEntity = productReposiroty.findById(id).orElseThrow(() -> new RecordNotFoundException(new Object[]{"de id " + id}));
            convert(product, productEntity);
            productEntity.setId(id);
            productReposiroty.save(productEntity);
        }catch (DataIntegrityViolationException dt){
            throw new RecordNotFoundException(new Object[]{"de id "+product.getIdRestaurant() + " do restaurant"});
        }
    }

    public void deleteProductOfRestaurant(Long id) {
        try {
            productReposiroty.deleteById(id);
        }catch (EmptyResultDataAccessException ex){
            throw new RecordNotExistsException(new Object[]{"de id "+id});
        }
    }
}
