package com.example.gerenciamentoTarefas.dto.User;

import lombok.Data;

@Data
public class UserRequest {
    private Long id;

    private String name;

    private String email;

    private String password;
}
