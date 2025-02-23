package com.example.gerenciamentoTarefas.dto.User;

import com.example.gerenciamentoTarefas.domain.enums.UserRoles;
import com.example.gerenciamentoTarefas.domain.model.Task;

import java.util.List;

public class UserResponse {
    private Long id;

    private String name;

    private String email;

    private String password;

    private UserRoles status;

    private List<Task> task;
}
