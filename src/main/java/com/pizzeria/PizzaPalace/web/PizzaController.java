package com.pizzeria.PizzaPalace.web;

import com.pizzeria.PizzaPalace.domain.services.PizzaService;
import com.pizzeria.PizzaPalace.persistence.entities.PizzaEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/available")
    public ResponseEntity<List<PizzaEntity>> getAvailable(){
        return ResponseEntity.ok(this.pizzaService.getAvailable());
    }

    @GetMapping("/{idPizza}")
    public ResponseEntity<PizzaEntity> getById(@PathVariable Long idPizza){
        return ResponseEntity.ok(this.pizzaService.getById(idPizza));
    }

    @GetMapping("/available/{name}")
    public ResponseEntity<PizzaEntity> getByName(@PathVariable String name){
        return ResponseEntity.ok(this.pizzaService.getByName(name));
    }

    @GetMapping("/with/{ingredient}")
    public ResponseEntity<List<PizzaEntity>> getWith(@PathVariable String ingredient){
        return ResponseEntity.ok(this.pizzaService.getWith(ingredient));
    }

    @GetMapping("/without/{ingredient}")
    public ResponseEntity<List<PizzaEntity>> getWithout(@PathVariable String ingredient){
        return ResponseEntity.ok(this.pizzaService.getWithout(ingredient));
    }

    @PostMapping("")
    public ResponseEntity<PizzaEntity> addPizza(@RequestBody PizzaEntity pizzaEntity){
        if (pizzaEntity.getPizzaId() == null || !this.pizzaService.exist(pizzaEntity.getPizzaId())) return ResponseEntity.ok(this.pizzaService.addPizza(pizzaEntity));

        return ResponseEntity.badRequest().build();
    }

    @PutMapping("")
    public ResponseEntity<PizzaEntity> updatePizza(@RequestBody PizzaEntity pizzaEntity){
        if (pizzaEntity.getPizzaId() == null || this.pizzaService.exist(pizzaEntity.getPizzaId())) return ResponseEntity.ok(this.pizzaService.addPizza(pizzaEntity));

        return ResponseEntity.badRequest().build();
    }

    @DeleteMapping("/{idPizza}")
    public ResponseEntity<String> deleteById(@PathVariable Long idPizza){
        if (!pizzaService.exist(idPizza)) return ResponseEntity.badRequest().build();
        this.pizzaService.deleteById(idPizza);
        return ResponseEntity.ok("Pizza deleted correctly!");
    }
}
