package com.pizzeria.PizzaPalace.domain.services;

import com.pizzeria.PizzaPalace.persistence.entities.OrderEntity;
import com.pizzeria.PizzaPalace.persistence.entityRepositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Service
public class OrderService {
    private final OrderRepository orderRepository;

    @Autowired
    public OrderService(OrderRepository orderRepository){
        this.orderRepository = orderRepository;
    }

    public List<OrderEntity> getAll(){
        return this.orderRepository.findAll();
    }
}
