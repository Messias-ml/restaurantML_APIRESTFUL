package com.messimari.restaurantml.domain.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "State")
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class StateEntity {

    @Id
    @EqualsAndHashCode.Include
    @Column(name = "ID_STATE", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Min(2)
    @NotEmpty
    @Column(name = "NAME_STATE", nullable = false)
    private String name;
}
