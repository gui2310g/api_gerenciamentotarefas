package com.example.gerenciamentoTarefas.dto.Task;

import com.example.gerenciamentoTarefas.domain.enums.StatusTask;
import lombok.Data;

import java.util.Date;

@Data
public class TaskResponse {
    private Long id;

    private String title;

    private String description;

    private StatusTask status;

    private Date dueDate;

    private Long userId;
}
