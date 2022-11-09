package com.messimari.restaurantml.domain.repository;

import com.messimari.restaurantml.domain.model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
}
