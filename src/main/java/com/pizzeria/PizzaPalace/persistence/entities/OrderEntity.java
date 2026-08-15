package com.pizzeria.PizzaPalace.persistence.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "pizza_order")
@Getter
@Setter
@NoArgsConstructor
public class OrderEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private Long orderID;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id")
    @Column(name = "customer_id")
    private Long customerID;
    @Column(nullable = false, columnDefinition = "TIMESTAMPTZ")
    private LocalDateTime date;
    @DecimalMin(value = "0.00", inclusive = true)
    @Column(nullable = false, precision = 12, scale = 2)
    private BigDecimal total;
    @Column(nullable = false)
    private String deliveryMethod;
    @Column(length = 200)
    private String additionalNotes;
}
