package com.messimari.restaurantml.domain.repository;

import com.messimari.restaurantml.domain.model.GroupEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GroupRepository extends JpaRepository<GroupEntity, Long> {
}
