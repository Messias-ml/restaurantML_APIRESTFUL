package com.messimari.restaurantml.domain.repository;

import com.messimari.restaurantml.domain.model.FormPaymentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FormPaymentRepository extends JpaRepository<FormPaymentEntity, Long> {
}
