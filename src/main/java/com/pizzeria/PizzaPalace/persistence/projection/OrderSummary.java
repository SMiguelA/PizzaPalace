package com.pizzeria.PizzaPalace.persistence.projection;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public interface OrderSummary {
    Long getIdOrder();
    String getCustomerName();
    LocalDateTime getOrderDate();
    BigDecimal getOrderTotal();
    String getPizzaNames();
}
