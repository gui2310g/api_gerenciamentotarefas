package com.example.gerenciamentoTarefas.dto.Task;

import lombok.Data;

@Data
public class TaskRequest {
    private Long id;

    private String title;

    private String description;
}
