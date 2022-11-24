package com.messimari.restaurantml.domain.repository;

import com.messimari.restaurantml.domain.model.RestaurantEntity;
import org.hibernate.query.NativeQuery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface RestaurantRepository extends JpaRepository<RestaurantEntity, Long> {

    @Query("Select r From RestaurantEntity r LEFT JOIN FETCH r.owner o where r.id = :id")
    Optional<RestaurantEntity> findByIdWithOwner(Long id);

    @Transactional
    @Modifying
    @Query(nativeQuery = true, value = "delete from user_restaurant where id_restaurant= :id")
    void deleteOwners(Long id);
}
