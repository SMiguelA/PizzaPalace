package com.pizzeria.PizzaPalace.persistence.audit;

import com.pizzeria.PizzaPalace.persistence.entities.PizzaEntity;
import jakarta.persistence.PostLoad;
import jakarta.persistence.PostPersist;
import jakarta.persistence.PostUpdate;
import jakarta.persistence.PreRemove;

public class AuditPizzaListener {

    @PostLoad
    public void postLoad(PizzaEntity pizzaEntity){}

    @PostPersist
    @PostUpdate
    public void onPostPersist(PizzaEntity pizzaEntity) {}

    @PreRemove
    public void onPreDelete(PizzaEntity pizzaEntity){}
}
