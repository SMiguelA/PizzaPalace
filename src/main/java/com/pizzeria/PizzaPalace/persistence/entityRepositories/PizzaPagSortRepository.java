package com.pizzeria.PizzaPalace.persistence.entityRepositories;

import com.pizzeria.PizzaPalace.persistence.entities.PizzaEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.ListPagingAndSortingRepository;

public interface PizzaPagSortRepository extends ListPagingAndSortingRepository<PizzaEntity, Long> {
    Page<PizzaEntity> findByIsAvailableTrue(Pageable pageable);
}
