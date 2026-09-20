package com.pizzeria.PizzaPalace.domain.services;

import com.pizzeria.PizzaPalace.persistence.entities.PizzaEntity;
import com.pizzeria.PizzaPalace.persistence.entityRepositories.PizzaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PizzaService {
    // Usamos la template de jdbc para este service
    // Este template me permite generar consultas SQL desde java hasta la BD - convierte el resultado en clases java
//    private final JdbcTemplate jdbcTemplate;
    private final PizzaRepository pizzaRepository;

    // inyeccion de dependencias de los componentes dentro del constructor
    @Autowired
    public PizzaService(PizzaRepository pizzaRepository) {
        this.pizzaRepository = pizzaRepository;
    }

    // Se pueden crear consultas de esta forma, pero no es la optima ya que escribimos SQL dentro del codigo
    public List<PizzaEntity> getAll(){
        // Mandamos consulta SQL y le decimos que me devuleva el resultado de forma PizzaEntity usando el Bean Mapper
//        return this.jdbcTemplate.query("SELECT * FROM pizza", new BeanPropertyRowMapper<>(PizzaEntity.class));
        return this.pizzaRepository.findAll();
    }

    public PizzaEntity getById(Long id){
        return this.pizzaRepository.findById(id).orElse(null);
    }

    public PizzaEntity getByName(String name){
        return this.pizzaRepository.findAllByIsAvailableTrueAndNameIgnoreCase(name);
    }

    public List<PizzaEntity> getAvailable(){
        return this.pizzaRepository.findAllByIsAvailableTrueOrderByPrice();
    }

    public List<PizzaEntity> getWith(String ingredient){
        return this.pizzaRepository.findAllByIsAvailableTrueAndDescriptionContainingIgnoreCase(ingredient);
    }

    public List<PizzaEntity> getWithout(String ingredient){
        return this.pizzaRepository.findAllByIsAvailableTrueAndDescriptionNotContainingIgnoreCase(ingredient);
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

}
