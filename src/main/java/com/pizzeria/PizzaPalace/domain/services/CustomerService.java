package com.pizzeria.PizzaPalace.domain.services;

import com.pizzeria.PizzaPalace.persistence.entities.CustomerEntity;
import com.pizzeria.PizzaPalace.persistence.entityRepositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {
    private final CustomerRepository customerRepository;

    @Autowired
    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public List<CustomerEntity> findAll(){
        return this.customerRepository.findAll();
    }

    public CustomerEntity findByPhone(String phone){
        return this.customerRepository.findByPhone(phone);
    }
}
