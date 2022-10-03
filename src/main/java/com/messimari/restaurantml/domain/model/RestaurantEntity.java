package com.messimari.restaurantml.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "Restaurant")
public class RestaurantEntity {

    @EqualsAndHashCode.Include
    @Id
    @Column(name = "ID_RESTAURANT")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Embedded
    private Address address;

    @Column(name = "NAME_RESTAURANT", nullable = false)
    private String name;

    @Column(name = "TAX_FRETE", nullable = false)
    private BigDecimal taxFrete;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_KITCHEN", nullable = false)
    private KitchenEntity kitchen;

    @ManyToMany
    @JoinTable(name = "Form_Payment_Restaurant",
            joinColumns = @JoinColumn(name = "ID_RESTAURANT"),
            inverseJoinColumns = @JoinColumn(name = "ID_FORM_PAYMENT"))
    private List<FormPaymentEntity> formPayment = new ArrayList<>();

    @CreationTimestamp
    @Column(nullable = false, columnDefinition = "datetime")
    private LocalDateTime creationDate;

    @UpdateTimestamp
    @Column(nullable = false, columnDefinition = "datetime")
    private LocalDateTime updateDate;

    @JsonIgnore
    @OneToMany(mappedBy = "restaurant")
    private List<ProductEntity> product;
}