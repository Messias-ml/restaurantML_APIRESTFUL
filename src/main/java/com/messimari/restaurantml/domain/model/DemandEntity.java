package com.messimari.restaurantml.domain.model;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "Demand")
public class DemandEntity {

    @Id
    @Column(name = "ID_DEMAND", nullable = false)
    private Long id;

    private BigDecimal subtotal;

    @Column(name = "TAX_FRETE", nullable = false)
    private BigDecimal taxFrete;

    private BigDecimal totalValue;

    @Embedded
    private Address address;

    @OneToMany(mappedBy = "demand")
    private List<ItemDemandEntity> items = List.of();

    @ManyToOne
    @JoinColumn(name = "id_restaurant", nullable = false)
    private RestaurantEntity restaurant;

    @ManyToOne
    @JoinColumn(name = "id_client", nullable = false)
    private UserEntity client;

    @ManyToOne
    @JoinColumn(name = "id_form_payment", nullable = false)
    private FormPaymentEntity formPayment;

    private StatusDemand status;

    @CreationTimestamp
    @Column(nullable = false, columnDefinition = "datetime")
    private LocalDateTime creationDate;

    @Column(columnDefinition = "datetime")
    private LocalDateTime confirmationDate;

    @Column(nullable = false, columnDefinition = "datetime")
    private LocalDateTime cancellationDate;

    @Column(nullable = false, columnDefinition = "datetime")
    private LocalDateTime deliveryDate;

}
