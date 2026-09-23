package com.pizzeria.PizzaPalace.domain.services;

import com.pizzeria.PizzaPalace.domain.dto.UpdatePizzaPriceDto;
import com.pizzeria.PizzaPalace.persistence.entities.PizzaEntity;
import com.pizzeria.PizzaPalace.persistence.entityRepositories.PizzaPagSortRepository;
import com.pizzeria.PizzaPalace.persistence.entityRepositories.PizzaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
public class PizzaService {
    // Usamos la template de jdbc para este service
    // Este template me permite generar consultas SQL desde java hasta la BD - convierte el resultado en clases java
//    private final JdbcTemplate jdbcTemplate;
    private final PizzaRepository pizzaRepository;
    private final PizzaPagSortRepository pizzaPagSortRepository;

    // inyeccion de dependencias de los componentes dentro del constructor
    @Autowired
    public PizzaService(PizzaRepository pizzaRepository, PizzaPagSortRepository pizzaPagSortRepository) {
        this.pizzaRepository = pizzaRepository;
        this.pizzaPagSortRepository = pizzaPagSortRepository;
    }

    // Se pueden crear consultas de esta forma, pero no es la optima ya que escribimos SQL dentro del codigo
    public Page<PizzaEntity> getAll(int page, int elements){
        // Mandamos consulta SQL y le decimos que me devuleva el resultado de forma PizzaEntity usando el Bean Mapper
//        return this.jdbcTemplate.query("SELECT * FROM pizza", new BeanPropertyRowMapper<>(PizzaEntity.class));
        PageRequest pageRequest = PageRequest.of(page, elements);
        return this.pizzaPagSortRepository.findAll(pageRequest);
    }

    public PizzaEntity getById(Long id){
        return this.pizzaRepository.findById(id).orElse(null);
    }

    public PizzaEntity getByName(String name){
        return this.pizzaRepository.findFirstByIsAvailableTrueAndNameIgnoreCase(name).orElseThrow(() -> new RuntimeException("Pizza does not exists"));
    }

    public Page<PizzaEntity> getAvailable(int page, int elements, String sortBy, String sortDirection){
        Sort sort = Sort.by(Sort.Direction.fromString(sortDirection), sortBy);
        PageRequest pageRequest = PageRequest.of(page, elements, Sort.by(Sort.Direction.fromString(sortDirection), sortBy));
        System.out.println(this.pizzaRepository.countAllByIsVeganTrue());
        return this.pizzaPagSortRepository.findByIsAvailableTrue(pageRequest);
    }

    public List<PizzaEntity> getWith(String ingredient){
        return this.pizzaRepository.findAllByIsAvailableTrueAndDescriptionContainingIgnoreCase(ingredient);
    }

    public List<PizzaEntity> getWithout(String ingredient){
        return this.pizzaRepository.findAllByIsAvailableTrueAndDescriptionNotContainingIgnoreCase(ingredient);
    }

    public List<PizzaEntity> getTopCheap(BigDecimal price){
        return this.pizzaRepository.findTop3ByIsAvailableTrueAndPriceLessThanEqualOrderByPriceAsc(price);
    }

    public PizzaEntity addPizza(PizzaEntity pizzaEntity){
        return this.pizzaRepository.save(pizzaEntity);
    }

    public boolean exist(Long idPizza){
        return this.pizzaRepository.existsById(idPizza);
    }

    public void deleteById(Long idPizza){
        this.pizzaRepository.deleteById(idPizza);
    }

    @Transactional
    public void updatePrice(UpdatePizzaPriceDto dto){
        this.pizzaRepository.updatePrice(dto);
    }
}
