package com.messimari.restaurantml.domain.service;

import com.messimari.restaurantml.api.model.dto.permition.PermitionDTO;
import com.messimari.restaurantml.domain.exception.RecordNotExistsException;
import com.messimari.restaurantml.domain.model.PermitionEntity;
import com.messimari.restaurantml.domain.repository.PermitionRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.List;

import static com.messimari.restaurantml.core.ModelMapperConvert.convert;
import static com.messimari.restaurantml.core.ModelMapperConvert.convertList;

@Service
@AllArgsConstructor
public class PermitionService {

    private final PermitionRepository repository;

    public void createPermition(PermitionDTO permition) {
        if (ObjectUtils.isEmpty(permition)){
            throw new RecordNotExistsException(new Object[]{"permition"});
        }
        repository.save(convert(permition, PermitionEntity.class));
    }

    public List<PermitionDTO> findAllPermitions() {
        List<PermitionEntity> allPermitions = repository.findAll();
        return convertList(allPermitions, PermitionDTO.class);
    }
}
