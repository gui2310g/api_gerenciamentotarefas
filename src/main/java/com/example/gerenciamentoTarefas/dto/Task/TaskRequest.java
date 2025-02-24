package com.example.gerenciamentoTarefas.dto.Task;

import com.example.gerenciamentoTarefas.domain.model.User;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TaskRequest {
    private Long id;

    private String title;

    private String description;

    private User user;
}
