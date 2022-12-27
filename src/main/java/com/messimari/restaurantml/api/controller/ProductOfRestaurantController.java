package com.messimari.restaurantml.api.controller;

import com.messimari.restaurantml.api.model.dto.photo.PhotoDTO;
import com.messimari.restaurantml.api.model.dto.photo.PhotoResponseDTO;
import com.messimari.restaurantml.api.model.dto.product.ProductCompleteDTO;
import com.messimari.restaurantml.api.model.dto.product.ProductRequestDTO;
import com.messimari.restaurantml.api.model.dto.product.ProductResponseDTO;
import com.messimari.restaurantml.domain.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/restaurants")
public class ProductOfRestaurantController {
    @Autowired
    private ProductService service;

    private static final String URI_ID_RESTAURANT = "/{idRestaurant}/product";
    private static final String URI = "/product";

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(URI)
    public void createProductToRestaurant(@RequestBody @Valid ProductRequestDTO product){
        service.createProductToRestaurant(product);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(URI_ID_RESTAURANT)
    public List<ProductResponseDTO> findProductsOfRestaurant(@PathVariable Long idRestaurant){
        return service.findProductsOfRestaurant(idRestaurant);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(URI_ID_RESTAURANT+"/{id}")
    public ProductCompleteDTO findByIdProductOfRestaurant(@PathVariable Long idRestaurant, @PathVariable Long id){
        return service.findByIdProductOfRestaurant(idRestaurant, id);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(URI+"/{id}")
    public ProductCompleteDTO findProductById(@PathVariable(value = "id") Long id){
        return service.findProductById(id);
    }


    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping(URI+"/{id}")
    public void updateProductOfRestaurant(@PathVariable Long id, @RequestBody @Valid ProductRequestDTO product){
        service.updateProductOfRestaurantById(id, product);
    }

    @ResponseStatus(HttpStatus.OK)
    @PatchMapping(value = URI+"/{idProduct}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public PhotoResponseDTO updatePhotoOfProduct(@PathVariable Long idProduct, @Valid PhotoDTO photo){
        return service.updatePhotoOfProduct(idProduct, photo);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping(URI+"/{id}")
    public void deleteProductOfRestaurant(@PathVariable Long id){
        service.deleteProductOfRestaurant(id);
    }
}
