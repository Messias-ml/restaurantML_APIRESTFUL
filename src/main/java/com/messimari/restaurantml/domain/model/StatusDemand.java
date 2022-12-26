package com.messimari.restaurantml.domain.model;

import lombok.Getter;

@Getter
public enum StatusDemand {

    CRIADO("Criado"),
    CONFIRMADO("Confirmado"),
    ENTREGUE("Entregue"),
    CANCELADO("Cancelado");

    private String description;

    StatusDemand(String description) {
        this.description = description;
    }
}
