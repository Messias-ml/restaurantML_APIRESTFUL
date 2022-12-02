package com.messimari.restaurantml.domain.repository;

import com.messimari.restaurantml.domain.model.DemandEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DemandRepository extends JpaRepository<DemandEntity, Long> {

    @Query("Select d from DemandEntity d LEFT JOIN FETCH d.restaurant r LEFT JOIN FETCH d.client LEFT JOIN FETCH d.items i LEFT JOIN FETCH i.product where r.id = :id")
    List<DemandEntity> findAllByIdRestaurant(Long id);
}