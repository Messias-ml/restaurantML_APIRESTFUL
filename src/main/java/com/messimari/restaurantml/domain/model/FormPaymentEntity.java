package com.messimari.restaurantml.domain.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "Form_Payment")
public class FormPaymentEntity {

    @Id
    @EqualsAndHashCode.Include
    @Column(name = "ID_FORM_PAYMENT", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "DESCRIPTION", nullable = false)
    private String description;
}
