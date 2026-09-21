package com.pizzeria.PizzaPalace.persistence.entityRepositories;

import com.pizzeria.PizzaPalace.persistence.entities.PizzaEntity;
import org.springframework.data.repository.ListCrudRepository;

import java.util.List;

public interface PizzaRepository extends ListCrudRepository<PizzaEntity, Long> {
    // Query method para que me de las pizzas que esten disponibles (available column mayor a 1) y me order resultados por precio
    List<PizzaEntity> findAllByIsAvailableTrueOrderByPrice();
    PizzaEntity findAllByIsAvailableTrueAndNameIgnoreCase(String name);
    List<PizzaEntity> findAllByIsAvailableTrueAndDescriptionContainingIgnoreCase(String description);
    List<PizzaEntity> findAllByIsAvailableTrueAndDescriptionNotContainingIgnoreCase(String description);
    int countAllByIsVeganTrue();
}
