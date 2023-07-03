package com.messimari.restaurantml.domain.model.assemble;

import com.messimari.restaurantml.api.controller.KitchenController;
import com.messimari.restaurantml.api.model.dto.kitchen.KitchenDTO;
import com.messimari.restaurantml.domain.model.KitchenEntity;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import static com.messimari.restaurantml.core.modelMapper.ModelMapperConvert.convert;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@Component
public class KitchenModelAssemble extends RepresentationModelAssemblerSupport<KitchenEntity, KitchenDTO> {

    public KitchenModelAssemble() {
        super(KitchenController.class, KitchenDTO.class);
    }

    @Override
    public KitchenDTO toModel(KitchenEntity entity) {
        KitchenDTO kitchenDTO = convert(entity, KitchenDTO.class);
        kitchenDTO.add(linkTo(KitchenController.class).withRel("Cozinhas"));
        return kitchenDTO;
    }
}
