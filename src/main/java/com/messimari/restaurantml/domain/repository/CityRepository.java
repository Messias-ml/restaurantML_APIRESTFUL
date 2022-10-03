package com.messimari.restaurantml.domain.repository;

import com.messimari.restaurantml.domain.model.CityEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CityRepository extends JpaRepository<CityEntity, Long> {
}
