package com.pizzeria.PizzaPalace.persistence.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Table(name = "order_item")
// Indicamos que la clase es una clase compuesta y utilizamos dos ID's para ella
// Indicamos que la clase OrderItemId tiene los atributos de la clave primaria
// Lo usamos para implementar una clave primaria compuesta usando idorderitem y idorder
@IdClass(OrderItemId.class)
@Getter
@Setter
@NoArgsConstructor
public class OrderItemEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_item_id")
    private Long orderItemId;

    @Id
    @Column(name = "order_id", nullable = false)
    private Long orderId;

    @Column(name = "pizza_id",  nullable = false)
    private Long pizzaId;

    @Column(nullable = false)
    private int quantity;

    @Column(nullable = false, precision = 12, scale = 2, columnDefinition = "numeric(12,2)")
    private BigDecimal price;

    // relacion uno a uno
    @OneToOne
    // indicamos con que columna se hace el join de la informacion
    // los ultimos dos parametros son para que no se actualice la tabla ni cree otra entidad pizza sino que solo junte informacion
    @JoinColumn(name = "pizza_id", referencedColumnName = "pizza_id", insertable = false, updatable = false)
    private PizzaEntity pizza;

    // OJO - Many to one y one to many funcionaran diferente
    // si esta entidad pueden ser varios para uno solo, entonces es many to one
    // si esta entidad es solo una para varios es one to many
    @ManyToOne
    @JoinColumn(name = "order_id", referencedColumnName = "order_id", insertable = false, updatable = false)
    private OrderEntity order;
}
