package com.pizzeria.PizzaPalace.persistence.entityRepositories;

import com.pizzeria.PizzaPalace.domain.dto.UpdatePizzaPriceDto;
import com.pizzeria.PizzaPalace.persistence.entities.PizzaEntity;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface PizzaRepository extends ListCrudRepository<PizzaEntity, Long> {
    // Query method para que me de las pizzas que esten disponibles (available column mayor a 1) y me order resultados por precio
    List<PizzaEntity> findAllByIsAvailableTrueOrderByPrice();
    Optional<PizzaEntity> findFirstByIsAvailableTrueAndNameIgnoreCase(String name);
    List<PizzaEntity> findAllByIsAvailableTrueAndDescriptionContainingIgnoreCase(String description);
    List<PizzaEntity> findAllByIsAvailableTrueAndDescriptionNotContainingIgnoreCase(String description);
    int countAllByIsVeganTrue();
    List<PizzaEntity> findTop3ByIsAvailableTrueAndPriceLessThanEqualOrderByPriceAsc(BigDecimal price);

    // Esto mismo se puede realizar de otra forma
    // @Query(value = "UPDATE pizza SET price =:price WHERE pizza.Pizza_id = :id ", nativeQuery = true)
    // void updatePrice(@Param("id") Long id, @Param("price") BigDecimal price);
    @Query(value = "UPDATE pizza SET price = :#{#newPizzaPrice.newPrice()} WHERE pizza_id = :#{#newPizzaPrice.pizzaId()}", nativeQuery = true)
    @Modifying
    void updatePrice(@Param("newPizzaPrice")UpdatePizzaPriceDto newPizzaPrice);

    //Cualquiera de las dos formas esta bien

}
