package com.example.gerenciamentoTarefas.dto.Login;

import lombok.Data;

@Data
public class LoginRequest {
    private String email;

    private String password;
}
