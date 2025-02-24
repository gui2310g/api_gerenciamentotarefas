package com.example.gerenciamentoTarefas.domain.model;

import com.example.gerenciamentoTarefas.domain.enums.UserRoles;
import com.example.gerenciamentoTarefas.dto.User.UserRequest;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String email;

    @Enumerated(EnumType.STRING)
    private UserRoles status = UserRoles.USER;

    @Column(nullable = false)
    private String password;

    @OneToMany(mappedBy = "user")
    private List<Task> task;

    public User(UserRequest dto) {
        this.name = dto.getName();
        this.email = dto.getEmail();
        this.password = dto.getPassword();
    }
}
