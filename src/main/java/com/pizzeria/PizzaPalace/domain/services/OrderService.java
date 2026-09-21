package com.pizzeria.PizzaPalace.domain.services;

import com.pizzeria.PizzaPalace.persistence.entities.OrderEntity;
import com.pizzeria.PizzaPalace.persistence.entityRepositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@Service
public class OrderService {
    private final String DELIVERY = "D";
    private final String CARRYOUT = "C";

    private final OrderRepository orderRepository;

    @Autowired
    public OrderService(OrderRepository orderRepository){
        this.orderRepository = orderRepository;
    }

    public List<OrderEntity> getAll(){
        return this.orderRepository.findAll();
    }

    public List<OrderEntity> getByDate(LocalDateTime date){
        return this.orderRepository.findAllByDateAfter(date);
    }

    public List<OrderEntity> getByDeliveryMethod(){
        List<String> methods = Arrays.asList(DELIVERY, CARRYOUT);
        return this.orderRepository.findAllByDeliveryMethodIn(methods);
    }
}
