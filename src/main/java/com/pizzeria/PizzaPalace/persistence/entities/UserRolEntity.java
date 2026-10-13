package com.pizzeria.PizzaPalace.persistence.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "user_role")
@IdClass(UserRolId.class)
@Getter
@Setter
@NoArgsConstructor
// Para Id's compuestos, siempre se debe crear una clase id donde implementemos la interfaz Serializable con atributos que sean los dos atributos para la llave compuesta
public class UserRolEntity {
    @Id
    @Column(nullable = false)
    private String role;

    @Id
    @Column(nullable = false, name = "username")
    private String username;

    @Column(nullable = false, name = "granted_date")
    private LocalDateTime grantedDate;

    @ManyToOne
    @JoinColumn(name = "username", referencedColumnName = "userName", insertable = false)
    private UserEntity user;
}
