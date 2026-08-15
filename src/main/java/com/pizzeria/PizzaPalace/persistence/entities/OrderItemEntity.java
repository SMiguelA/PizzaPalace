package com.pizzeria.PizzaPalace.persistence.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Table(name = "order_item")
@Getter
@Setter
@NoArgsConstructor
public class OrderItemEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_item_id")
    private Long orderItemID;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    @Column(name = "order_id", nullable = false)
    private Long orderID;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pizza_id")
    @Column(name = "pizza_id",  nullable = false)
    private Long pizzaID;
    @Column(nullable = false, precision = 12, scale = 2)
    private float quantity;
    @Column(nullable = false, precision = 12, scale = 2)
    private BigDecimal price;
}
