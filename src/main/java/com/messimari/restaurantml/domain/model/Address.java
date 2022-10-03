package com.messimari.restaurantml.domain.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Embeddable
public class Address {

    @Column(name = "ADDRESS_ZIP_COD")
    private String zipCode;

    @Column(name = "ADDRESS_PATIO")
    private String patio;

    @Column(name = "ADDRESS_NUMBER", length = 4)
    private Integer number;

    @Column(name = "ADDRESS_COMPLEMENT")
    private String complement;

    @Column(name = "ADDRESS_NEIGHBORHOOD")
    private String neighborhood;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ADDRESS_ID_CITY")
    private CityEntity city;
}
