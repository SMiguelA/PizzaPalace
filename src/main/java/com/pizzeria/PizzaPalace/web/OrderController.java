package com.pizzeria.PizzaPalace.web;

import com.pizzeria.PizzaPalace.domain.services.OrderService;
import com.pizzeria.PizzaPalace.persistence.entities.OrderEntity;
import com.pizzeria.PizzaPalace.persistence.projection.OrderSummary;
import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService){
        this.orderService = orderService;
    }

    @GetMapping("")
    public ResponseEntity<List<OrderEntity>> getAll(){
        return ResponseEntity.ok(this.orderService.getAll());
    }

    @GetMapping("/{date}")
    public ResponseEntity<List<OrderEntity>> getByDate(@PathVariable String date){
        return ResponseEntity.ok(this.orderService.getByDate(LocalDate.parse(date).atStartOfDay()));
    }

    @GetMapping("/delivery")
    public ResponseEntity<List<OrderEntity>> getByMethod(){
        return ResponseEntity.ok(this.orderService.getByDeliveryMethod());
    }

    @GetMapping("/customer/{id}")
    public ResponseEntity<List<OrderEntity>> getCustomerOrders(@PathVariable Long id){
        return ResponseEntity.ok(this.orderService.getCustomerOrders(id));
    }

    @GetMapping("/summary/{id}")
    public ResponseEntity<OrderSummary> getSummary(@PathVariable Long id){
        return ResponseEntity.ok(this.orderService.getSummary(id));
    }
}
