package com.example.gerenciamentoTarefas.domain.repository;

import com.example.gerenciamentoTarefas.domain.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {
    List<Task> findAllByUserId(Long userId);
}
