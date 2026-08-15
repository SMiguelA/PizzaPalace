package com.pizzeria.PizzaPalace.persistence.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Table(name = "pizza")
// Crea sus getters y setters, tambien le dice que no recibe ningun argumento para el contructor
@Getter
@Setter
@NoArgsConstructor
public class PizzaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pizza_id", unique = true, nullable = false)
    private Long pizzaID;
    @Column(unique = true, nullable = false, length = 50)
    private String name;
    @Column(nullable = false, length = 150)
    private String description;
    @DecimalMin(value = "0.00", inclusive = true)
    @Column(nullable = false, precision = 12, scale = 2)
    private BigDecimal price;
    @Column(nullable = false)
    private boolean isVegetarian;
    @Column(nullable = false)
    private boolean isVegan;
    @Column(nullable = false)
    private boolean isAvailable;
}
