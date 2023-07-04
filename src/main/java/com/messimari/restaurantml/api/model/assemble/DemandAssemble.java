package com.messimari.restaurantml.api.model.assemble;

import com.messimari.restaurantml.api.controller.CityController;
import com.messimari.restaurantml.api.controller.DemandController;
import com.messimari.restaurantml.api.model.dto.demand.DemandToRestaurantDTO;
import com.messimari.restaurantml.core.modelMapper.ModelMapperConvert;
import com.messimari.restaurantml.domain.model.DemandEntity;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import java.util.List;

import static com.messimari.restaurantml.core.modelMapper.ModelMapperConvert.convert;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class DemandAssemble extends RepresentationModelAssemblerSupport<DemandEntity, DemandToRestaurantDTO> {


    public DemandAssemble() {
        super(DemandController.class, DemandToRestaurantDTO.class);
    }

    @Override
    public DemandToRestaurantDTO toModel(DemandEntity demandEntity) {
        DemandToRestaurantDTO demand = convert(demandEntity, DemandToRestaurantDTO.class);
        demand.add(linkTo(methodOn(DemandController.class).findByIdDemand(demandEntity.getId())).withRel("demand"));
        return demand;
    }//ver onde esta o erro.
}
