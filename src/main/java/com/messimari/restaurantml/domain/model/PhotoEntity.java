package com.messimari.restaurantml.domain.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "photo_product")
public class PhotoEntity {

    @Id
    @Column(name = "ID_PRODUCT")
    private Long id;

    @Column(name = "name_file")
    private String nameFile;

    @MapsId
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_PRODUCT")
    private ProductEntity product;

    @Column(name = "description")
    private String description;

    @Column(name = "content_type", nullable = false)
    private String contentType;

    @Column(name = "size_file", nullable = false)
    private Long size;
}
