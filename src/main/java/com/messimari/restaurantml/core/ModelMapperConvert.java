package com.messimari.restaurantml.core;

import com.messimari.restaurantml.domain.exception.RecordNotExistsException;
import com.messimari.restaurantml.domain.exception.RecordNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static com.messimari.restaurantml.core.ModelMapperConfig.modelMapper;

public class ModelMapperConvert {

    public static <T> T convert(Object objectWithContent, Class<T> classCopy) {
        if (ObjectUtils.isEmpty(objectWithContent)) {
            throw new RecordNotFoundException(new Object[]{"objeto com conteúdo dentro da conversão do modelMap"});
        }
        ModelMapper modelMapper = modelMapper();
        return modelMapper.map(objectWithContent, classCopy);
    }

    public static <T> T convert(Object objectWithContent, T objectNecessary) {
        if (ObjectUtils.isEmpty(objectWithContent)) {
            throw new RecordNotFoundException(new Object[]{"objeto com conteúdo dentro da conversão do modelMap"});
        } else {
            ModelMapper modelMapper = modelMapper();
            modelMapper.map(objectWithContent, objectNecessary);
            return objectNecessary;
        }
    }

    public static <T> List<T> convertList(Collection<?> listWithContent, Class<T> classCopy) {
        if (CollectionUtils.isEmpty(listWithContent)) {
            throw new RecordNotExistsException(new Object[]{"que contém uma lista de conteúdos"});
        } else {
            ModelMapper modelMapper = modelMapper();
            return listWithContent.stream()
                    .map(c -> modelMapper.map(c, classCopy))
                    .collect(Collectors.toList());
        }
    }

    public static <T> Set<T> convertSet(Collection<?> listWithContent, Class<T> classCopy) {
        if (CollectionUtils.isEmpty(listWithContent)) {
            throw new RecordNotExistsException(new Object[]{"que contém uma lista de conteúdos"});
        } else {
            ModelMapper modelMapper = modelMapper();
            return listWithContent.stream()
                    .map(c -> modelMapper.map(c, classCopy))
                    .collect(Collectors.toSet());
        }
    }
























/*
    private static <T> T getDesiredObject(Object objectWithContent, Class<T> classCopy) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(objectWithContent, classCopy);
    }

    public static <T> T convertMap(Object objectWithContent, Class<T> classCopy){
        if (ObjectUtils.isEmpty(objectWithContent)) {
            throw new RecordNotExistsException(new Object[]{objectWithContent.getClass().getName()});
        }
        return getDesiredObject(objectWithContent, classCopy);
    }

    public static <T> List<T> convertListMap(List<?> objectsWithContent, Class<T> classCopy){
        ModelMapper modelMapper = new ModelMapper();
        if (CollectionUtils.isEmpty(objectsWithContent)) {
            throw new RecordNotExistsException(new Object[]{objectsWithContent.getClass().getName()});
        }

        return objectsWithContent.stream()
                .map(o -> modelMapper.map(o ,classCopy))
                .collect(Collectors.toList());
    }*/
}
