package com.messimari.restaurantml.domain.repository;

import com.messimari.restaurantml.domain.model.KitchenEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface KitchenRepository extends JpaRepository<KitchenEntity, Long> {
}
