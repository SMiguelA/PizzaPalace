package com.pizzeria.PizzaPalace.web;

import com.pizzeria.PizzaPalace.domain.dto.UpdatePizzaPriceDto;
import com.pizzeria.PizzaPalace.domain.services.PizzaService;
import com.pizzeria.PizzaPalace.persistence.entities.PizzaEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
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
    public ResponseEntity<Page<PizzaEntity>> getAllPizzas(@RequestParam(defaultValue = "0") int page,
                                                          @RequestParam(defaultValue = "8") int elements){
        return ResponseEntity.ok(this.pizzaService.getAll(page, elements));
    }

    @GetMapping("/available")
    public ResponseEntity<Page<PizzaEntity>> getAvailable(@RequestParam(defaultValue = "0") int page,
                                                          @RequestParam(defaultValue = "8") int elements,
                                                          @RequestParam(defaultValue = "price") String sortBy,
                                                          @RequestParam(defaultValue = "ASC") String sortDirection){
        return ResponseEntity.ok(this.pizzaService.getAvailable(page, elements, sortBy, sortDirection));
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

    @GetMapping("/price/{price}")
    public ResponseEntity<List<PizzaEntity>> getCheaper(@PathVariable BigDecimal price){
        return ResponseEntity.ok(this.pizzaService.getTopCheap(price));
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

    @PutMapping("/price")
    public ResponseEntity<Void> updatePrice(@RequestBody UpdatePizzaPriceDto dto){
        if (this.pizzaService.exist(dto.pizzaId())){
            this.pizzaService.updatePrice(dto);
            return ResponseEntity.ok().build();
        }

        return ResponseEntity.badRequest().build();
    }

    @DeleteMapping("/{idPizza}")
    public ResponseEntity<String> deleteById(@PathVariable Long idPizza){
        if (!pizzaService.exist(idPizza)) return ResponseEntity.badRequest().build();
        this.pizzaService.deleteById(idPizza);
        return ResponseEntity.ok("Pizza deleted correctly!");
    }
}
