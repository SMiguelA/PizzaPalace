package com.pizzeria.PizzaPalace.domain.services;

import com.pizzeria.PizzaPalace.persistence.entities.UserEntity;
import com.pizzeria.PizzaPalace.persistence.entities.UserRolEntity;
import com.pizzeria.PizzaPalace.persistence.entityRepositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserSecurityService implements UserDetailsService {
    private final UserRepository userRepository;

    @Autowired
    public UserSecurityService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity userEntity = this.userRepository.findById(username).orElseThrow(() -> new UsernameNotFoundException("User "+ username+" not found."));

        // Mapeamos la lista de roles dentro de user entity, tomamos la lista que esta guardada
        // como adentro hay userrolentity's debemos mapearla para que sea un array
        // pasamos este array a la lista de roles que admitimos en la app
        String[] roles = userEntity.getRoles().stream().map(UserRolEntity::getRole).toArray(String[]::new);

        // Igual que en el SecurityConfig, pero con info desde la bd
        return User.builder()
                .username(userEntity.getUserName())
                .password(userEntity.getPassword())
                .roles(roles)
                .accountLocked(userEntity.getLocked())
                .disabled(userEntity.getDisabled())
                .build();
    }
}
