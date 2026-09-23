package com.pizzeria.PizzaPalace.domain.dto;

import java.math.BigDecimal;

public record UpdatePizzaPriceDto(
        Long pizzaId,
        BigDecimal newPrice
){

}
