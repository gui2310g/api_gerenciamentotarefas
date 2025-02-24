package com.example.gerenciamentoTarefas.domain;

import com.example.gerenciamentoTarefas.domain.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
