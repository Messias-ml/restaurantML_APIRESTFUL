package com.messimari.restaurantml.domain.repository;

import com.messimari.restaurantml.domain.model.PhotoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PhotoRepository extends JpaRepository<PhotoEntity, Long> {
}
