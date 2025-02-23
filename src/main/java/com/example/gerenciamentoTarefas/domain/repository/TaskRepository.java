package com.example.gerenciamentoTarefas.domain.repository;

import com.example.gerenciamentoTarefas.domain.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task, Long> {
}
