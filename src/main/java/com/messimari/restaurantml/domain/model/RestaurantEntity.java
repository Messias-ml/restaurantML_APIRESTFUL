package com.messimari.restaurantml.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "Restaurant")
@NoArgsConstructor

public class RestaurantEntity {

    @Id
    @Column(name = "ID_RESTAURANT")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonIgnore
    @Embedded
    private Address address;

    @Column(name = "NAME_RESTAURANT", nullable = false)
    private String name;

    @Column(name = "TAX_FRETE", nullable = false)
    private BigDecimal taxFrete;

    @ManyToOne
    @JoinColumn(name = "ID_KITCHEN", nullable = false)
    private KitchenEntity kitchen;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "Form_Payment_Restaurant",
            joinColumns = @JoinColumn(name = "ID_RESTAURANT"),
            inverseJoinColumns = @JoinColumn(name = "ID_FORM_PAYMENT"))
    private List<FormPaymentEntity> formPayment = new ArrayList<>();

    @CreationTimestamp
    @Column(nullable = false, columnDefinition = "datetime")
    private OffsetDateTime creationDate;

    @UpdateTimestamp
    @Column(nullable = false, columnDefinition = "datetime")
    private OffsetDateTime updateDate;

    @JsonIgnore
    @OneToMany(mappedBy = "restaurant")
    private List<ProductEntity> product;
}
