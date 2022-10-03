package com.messimari.restaurantml.domain.repository;

import com.messimari.restaurantml.domain.model.StateEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StateRepository extends JpaRepository<StateEntity, Long> {
}