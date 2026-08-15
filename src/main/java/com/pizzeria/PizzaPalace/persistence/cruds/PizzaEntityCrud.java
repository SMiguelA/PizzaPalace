package com.pizzeria.PizzaPalace.persistence.cruds;

import com.pizzeria.PizzaPalace.persistence.entities.PizzaEntity;
import org.springframework.data.repository.CrudRepository;

public interface PizzaEntityCrud extends CrudRepository<PizzaEntity, Long> {
}
