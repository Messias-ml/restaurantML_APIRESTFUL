package com.messimari.restaurantml.domain.repository;

import com.messimari.restaurantml.domain.model.RestaurantEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RestaurantRepository extends JpaRepository<RestaurantEntity, Long> {
}
