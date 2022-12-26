package com.messimari.restaurantml.domain.model;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
@Table(name = "Demand")
public class DemandEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_DEMAND", nullable = false)
    private Long id;

    private BigDecimal subtotal = BigDecimal.ZERO;

    @Column(name = "TAX_FRETE", nullable = false)
    private BigDecimal taxFrete;

    private BigDecimal totalValue = BigDecimal.ZERO;

    @Embedded
    private Address address;

    @OneToMany(mappedBy = "demand", cascade = CascadeType.ALL)
    private List<ItemDemandEntity> items = List.of();

    @ManyToOne
    @JoinColumn(name = "id_restaurant", nullable = false)
    private RestaurantEntity restaurant;

    @ManyToOne
    @JoinColumn(name = "id_client", nullable = false)
    private UserEntity client;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_form_payment", nullable = false)
    private FormPaymentEntity formPayment;

    @Enumerated(EnumType.STRING)
    private StatusDemand status = StatusDemand.CRIADO;

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
