package com.example.gerenciamentoTarefas.dto.User;

import com.example.gerenciamentoTarefas.domain.model.Task;
import lombok.Data;

import java.util.List;

@Data
public class UserResponse {
    private Long id;

    private String name;

    private String email;

    private String password;

    private List<Task> task;
}
