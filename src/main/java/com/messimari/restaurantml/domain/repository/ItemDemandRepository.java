package com.messimari.restaurantml.domain.repository;

import com.messimari.restaurantml.domain.model.ItemDemandEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemDemandRepository  extends JpaRepository<ItemDemandEntity, Long> {
}
