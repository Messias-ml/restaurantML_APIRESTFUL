package com.messimari.restaurantml.domain.model;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "Item_Demand")
public class ItemDemandEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_ITEM_DEMAND", nullable = false)
    private Long id;

    @Column(nullable = false)
    private Integer quantity;

    @Column(nullable = false)
    private BigDecimal unitaryPrice;

    @Column(nullable = false)
    private BigDecimal totalPrice;

    private String observation;

    @ManyToOne
    @JoinColumn(name = "id_demand", nullable = false)
    private DemandEntity demand;

    @ManyToOne
    @JoinColumn(name = "id_product", nullable = false)
    private ProductEntity product;

}
