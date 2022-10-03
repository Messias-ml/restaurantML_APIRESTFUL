package com.messimari.restaurantml.infrastructure.repository.specifications;

import com.messimari.restaurantml.domain.model.RestaurantEntity;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class RestaurantSpecification implements Specification<RestaurantEntity> {

    @Override
    public Predicate toPredicate(Root<RestaurantEntity> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        return null;
    }
}
