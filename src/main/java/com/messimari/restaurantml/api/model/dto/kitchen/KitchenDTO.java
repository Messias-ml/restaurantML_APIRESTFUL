package com.messimari.restaurantml.api.model.dto.kitchen;

import lombok.Data;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import java.util.Collection;

@Relation(collectionRelation = "Cozinhas")
@Data
public class KitchenDTO extends RepresentationModel<KitchenDTO> {

    private String name;
}
