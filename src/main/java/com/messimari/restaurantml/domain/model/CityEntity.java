package com.messimari.restaurantml.domain.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

@Entity
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Table(name = "City")
public class CityEntity {

    @Id
    @EqualsAndHashCode.Include
    @Column(name = "ID_CITY", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "NAME_CITY", nullable = false)
    private String name;

    @ManyToOne
    @JoinColumn(name = "ID_STATE", nullable = false)
    private StateEntity State;
}
