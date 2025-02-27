package com.example.gerenciamentoTarefas.domain.model;

import com.example.gerenciamentoTarefas.domain.enums.UserRoles;
import jakarta.persistence.*;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Entity
@Data
@Table(name = "users")
public class User implements UserDetails {

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

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if(this.status == UserRoles.ADMIN)  return List.of(
                new SimpleGrantedAuthority("ROLE_ADMIN"), new SimpleGrantedAuthority("ROLE_USER"));

        return List.of(new SimpleGrantedAuthority("ROLE_USER"));
    }

    @Override
    public String getUsername() { return email; }
}
