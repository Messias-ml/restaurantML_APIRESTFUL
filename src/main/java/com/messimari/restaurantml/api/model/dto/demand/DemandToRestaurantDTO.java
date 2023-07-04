package com.messimari.restaurantml.api.model.dto.demand;

import com.messimari.restaurantml.api.model.dto.user.UserBasicDTO;
import com.messimari.restaurantml.domain.model.StatusDemand;
import lombok.Data;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import java.math.BigDecimal;
import java.util.Collection;

@Relation(collectionRelation = "demands")
@Data
public class DemandToRestaurantDTO extends RepresentationModel<DemandToRestaurantDTO> {

    private BigDecimal totalValue;

    private BigDecimal taxFrete;

    private UserBasicDTO client;

    private StatusDemand status;

    private String nameRestaurant;
}
