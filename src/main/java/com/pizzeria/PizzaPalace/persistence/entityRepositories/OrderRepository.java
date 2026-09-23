package com.pizzeria.PizzaPalace.persistence.entityRepositories;

import com.pizzeria.PizzaPalace.persistence.entities.OrderEntity;
import com.pizzeria.PizzaPalace.persistence.projection.OrderSummary;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface OrderRepository extends ListCrudRepository<OrderEntity, Long> {
    List<OrderEntity> findAllByDateAfter(LocalDateTime date);
    List<OrderEntity> findAllByDeliveryMethodIn(List<String> methods);

    @Query(value = "SELECT * FROM pizza_order WHERE customer_id = :id", nativeQuery = true)
    List<OrderEntity> findCustomerOrders(@Param("id") Long idCustomer);

    @Query(value = """
            SELECT po.order_id AS idOrder, cu.name AS customerName, po.date AS orderDate, 
                   po.total AS orderTotal, STRING_AGG(PI.name, ', ') AS pizzaNames 
            FROM   pizza_order po 
            INNER JOIN customer cu ON po.customer_id = cu.customer_id 
            INNER JOIN order_item oi ON po.order_id = oi.order_id 
            INNER JOIN pizza PI ON oi.pizza_id = PI.pizza_id 
            WHERE po.order_id = :id 
            GROUP BY po.order_id, cu.name, po.total, po.date 
            """, nativeQuery = true)
    OrderSummary findSummary(@Param("id") Long orderId);
}
