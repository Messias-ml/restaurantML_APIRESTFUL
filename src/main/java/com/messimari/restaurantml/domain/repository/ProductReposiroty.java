package com.messimari.restaurantml.domain.repository;

import com.messimari.restaurantml.domain.model.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ProductReposiroty extends JpaRepository<ProductEntity, Long> {

    @Query("Select p from ProductEntity p LEFT JOIN FETCH p.restaurant pr where pr.id = :id")
    List<ProductEntity> findAllByIdRestaurant(Long id);

    @Query("Select p from ProductEntity p LEFT JOIN FETCH p.restaurant pr where pr.id = :id")
    Optional<ProductEntity> findByIdRestaurant(Long id);
}
