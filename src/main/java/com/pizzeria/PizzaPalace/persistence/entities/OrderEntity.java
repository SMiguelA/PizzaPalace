package com.pizzeria.PizzaPalace.persistence.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "pizza_order")
@Getter
@Setter
@NoArgsConstructor
public class OrderEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private Long orderId;

    @Column(name = "customer_id")
    private Long customerId;

    @Column(nullable = false, columnDefinition = "TIMESTAMPTZ")
    private LocalDateTime date;

    @DecimalMin(value = "0.00", inclusive = true)
    // el ultimo parametro es solo necesario por la version del hibernate utilizado
    @Column(nullable = false, precision = 12, scale = 2, columnDefinition = "numeric(12,2)")
    private BigDecimal total;

    @Column(nullable = false)
    private String deliveryMethod;

    @Column(length = 200)
    private String additionalNotes;

    @OneToOne
    @JoinColumn(name = "customer_id", referencedColumnName = "customer_id", insertable = false, updatable = false)
    private CustomerEntity customer;

    // referenciamos el nombre del atributo de la entidad donde realizamos el join, en este caso desde OrderItemEntity
    @OneToMany(mappedBy = "order")
    private List<OrderItemEntity> itemsAdded;
}
