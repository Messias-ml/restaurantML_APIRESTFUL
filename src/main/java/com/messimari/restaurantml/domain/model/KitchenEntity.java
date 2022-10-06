package com.messimari.restaurantml.domain.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "Kitchen")
public class KitchenEntity {

    @Id
    @EqualsAndHashCode.Include
    @Column(name = "ID_KITCHEN", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(name = "NAME_KITCHEN", nullable = false)
    private String name;
}
