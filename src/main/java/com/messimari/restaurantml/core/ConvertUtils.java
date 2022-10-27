package com.messimari.restaurantml.core;

import com.messimari.restaurantml.api.model.dto.restaurant.RestaurantResponseDTO;
import com.messimari.restaurantml.domain.exception.RecordNotExists;
import com.messimari.restaurantml.domain.model.RestaurantEntity;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class ConvertUtils {

    private static <T> T getDesiredObject(Object objectWithContent, Class<T> classCopy) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(objectWithContent, classCopy);
    }

    public static <T> T convert(Object objectWithContent, Class<T> classCopy) {
        T objectNecessary = null;

        if (!ObjectUtils.isEmpty(objectWithContent)) {
            objectNecessary = BeanUtils.instantiateClass(classCopy);
            try {
                BeanUtils.copyProperties(objectWithContent, objectNecessary);
            } catch (Exception ex) {
                System.out.println(ex);
                System.out.println(ex.getMessage());
                System.out.println(ex.getCause());
            }
        }
        return objectNecessary;
    }

    public static <T> List<T> convertList(List<?> objectsWithContent, Class<T> classCopy) {
        List<T> listNecessary = new ArrayList<>();

        if (!CollectionUtils.isEmpty(objectsWithContent)) {
            listNecessary = objectsWithContent
                    .stream()
                    .map(content -> convert(content, classCopy))
                    .collect(Collectors.toList());
        }
        return listNecessary;
    }

    public static <T> T convertMap(Object objectWithContent, Class<T> classCopy){
        if (ObjectUtils.isEmpty(objectWithContent)) {
            throw new RecordNotExists(new Object[]{objectWithContent.getClass().getName()});
        }
        return getDesiredObject(objectWithContent, classCopy);
    }

    public static <T> List<T> convertListMap(List<Object> objectsWithContent, Class<T> classCopy){
        ModelMapper modelMapper = new ModelMapper();
        if (CollectionUtils.isEmpty(objectsWithContent)) {
            throw new RecordNotExists(new Object[]{objectsWithContent.getClass().getName()});
        }

        return objectsWithContent.stream()
                .map(o -> modelMapper.map(o ,classCopy))
                .collect(Collectors.toList());
    }

    public static List<RestaurantResponseDTO> findRestaurantsWithNameKitchen(List<RestaurantEntity> restarantWithContent) {
        List<RestaurantResponseDTO> restaurants = convertList(restarantWithContent, RestaurantResponseDTO.class);
        AtomicInteger valor = new AtomicInteger();
        restaurants.forEach(r -> {
            r.setNameKitchen(restarantWithContent.get(valor.get()).getKitchen().getName());
            valor.set(valor.get() + 1);
        });
        return restaurants;
    }
}
