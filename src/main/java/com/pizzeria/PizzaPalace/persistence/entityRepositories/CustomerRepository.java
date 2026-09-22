package com.pizzeria.PizzaPalace.persistence.entityRepositories;

import com.pizzeria.PizzaPalace.persistence.entities.CustomerEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.data.repository.query.Param;

public interface CustomerRepository extends ListCrudRepository<CustomerEntity, Long> {
    @Query(value = "SELECT c FROM CustomerEntity c WHERE c.phone = :phone")
    CustomerEntity findByPhone(@Param("phone") String phone);
}
