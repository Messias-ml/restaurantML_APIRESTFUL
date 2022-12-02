package com.messimari.restaurantml.domain.repository;

import com.messimari.restaurantml.domain.model.FormPaymentEntity;
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
/*
    @Query("SELECT COUNT(fp) > 0 FROM RestaurantEntity r WHERE r.id = :idRestaurant" +
            " AND r.formPayment.id in (:idFormPayment)")
   boolean existFormPayment(FormPaymentEntity formPayment);

    @Query("select case when (count(*) > 0)  then true else false end from RestaurantEntity r " +
            "LEFT JOIN FETCH r.formPayment WHERE r.id = :idRestaurant AND ")
   boolean existFormPaymentw(FormPaymentEntity formPayment);
    */
    @Query("select r FROM RestaurantEntity r LEFT JOIN FETCH r.formPayment fp WHERE r.id = :idRestaurant AND "+
            "EXISTS (SELECT fp2 FROM FormPaymentEntity fp2 WHERE fp.id = :idFormPayment)")
    Optional<RestaurantEntity> existFormPayment(Long idRestaurant, Long idFormPayment);
}
