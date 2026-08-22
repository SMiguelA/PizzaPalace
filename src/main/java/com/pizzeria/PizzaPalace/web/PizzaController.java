package com.pizzeria.PizzaPalace.web;

import com.pizzeria.PizzaPalace.domain.services.PizzaService;
import com.pizzeria.PizzaPalace.persistence.entities.PizzaEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/pizzas")
public class PizzaController {
    private final PizzaService pizzaService;

    @Autowired
    public PizzaController(PizzaService pizzaService) {
        this.pizzaService = pizzaService;
    }

    @GetMapping("")
    public ResponseEntity<List<PizzaEntity>> getAllPizzas(){
        return ResponseEntity.ok(this.pizzaService.getAll());
    }
}
