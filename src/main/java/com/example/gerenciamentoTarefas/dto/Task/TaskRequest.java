package com.example.gerenciamentoTarefas.dto.Task;

import com.example.gerenciamentoTarefas.domain.enums.StatusTask;
import com.example.gerenciamentoTarefas.domain.model.User;
import lombok.Data;

@Data
public class TaskRequest {
    private Long id;

    private String title;

    private String description;

    private StatusTask status;
}
