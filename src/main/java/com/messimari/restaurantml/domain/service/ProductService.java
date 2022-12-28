package com.messimari.restaurantml.domain.service;

import com.messimari.restaurantml.api.model.dto.photo.PhotoDTO;
import com.messimari.restaurantml.api.model.dto.photo.PhotoResponseDTO;
import com.messimari.restaurantml.api.model.dto.product.ProductCompleteDTO;
import com.messimari.restaurantml.api.model.dto.product.ProductRequestDTO;
import com.messimari.restaurantml.api.model.dto.product.ProductResponseDTO;
import com.messimari.restaurantml.domain.exception.RecordNotExistsException;
import com.messimari.restaurantml.domain.exception.RecordNotFoundException;
import com.messimari.restaurantml.domain.model.PhotoEntity;
import com.messimari.restaurantml.domain.model.ProductEntity;
import com.messimari.restaurantml.domain.repository.PhotoRepository;
import com.messimari.restaurantml.domain.repository.ProductReposiroty;
import com.messimari.restaurantml.domain.repository.RestaurantRepository;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

import static com.messimari.restaurantml.core.ModelMapperConvert.convert;
import static com.messimari.restaurantml.core.ModelMapperConvert.convertList;

@Service
@AllArgsConstructor
public class ProductService {

    private final RestaurantRepository restaurantRepository;

    private final ProductReposiroty productReposiroty;

    private final PhotoRepository photoRepository;

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

    public PhotoResponseDTO updatePhotoOfProduct(Long idProduct, PhotoDTO photo) {
        ProductEntity productEntity = productReposiroty.findById(idProduct).orElseThrow(() -> new RecordNotFoundException(new Object[]{"de id " + idProduct}));
        /*String nameFile = "upload_".concat(photo.getPhoto().getOriginalFilename());
        Path filePhoto = Path.of("C:/Área de Trabalho", nameFile);
        try {
            photo.getPhoto().transferTo(filePhoto);
        } catch (IOException e) {
            e.printStackTrace();
        }*/
        PhotoEntity photoEntity = setPhotoEntity(productEntity, photo);
        photoRepository.save(photoEntity);
        return convert(photoEntity, PhotoResponseDTO.class);
    }

    private PhotoEntity setPhotoEntity(ProductEntity product, PhotoDTO photo) {
        PhotoEntity photoEntity = new PhotoEntity();
        photoEntity.setProduct(product);
        photoEntity.setNameFile(photo.getPhoto().getOriginalFilename());
        photoEntity.setDescription(photo.getDescription());
        photoEntity.setContentType(photo.getPhoto().getContentType());
        photoEntity.setSize(photo.getPhoto().getSize());
        return photoEntity;
    }
}


