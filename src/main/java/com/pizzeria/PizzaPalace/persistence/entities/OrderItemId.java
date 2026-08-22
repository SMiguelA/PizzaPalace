package com.pizzeria.PizzaPalace.persistence.entities;

import java.io.Serializable;
import java.util.Objects;

// aqui creamos la llave compuesta para orderitemid y orderid
// Una llave primaria compuesta es lo mismo que una llave primaria pero requiere de dos id's para localizar una fila en BD's
public class OrderItemId implements Serializable {
    private Long orderItemId;
    private Long orderId;

    // son dos metodos por defecto para crear la llave compuesta
    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        OrderItemId that = (OrderItemId) o;
        return Objects.equals(orderItemId, that.orderItemId) && Objects.equals(orderId, that.orderId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(orderItemId, orderId);
    }
}
