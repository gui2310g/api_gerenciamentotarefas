package com.example.gerenciamentoTarefas.dto.User;

import lombok.Data;


@Data
public class UserDetailsResponse {
    private Long id;

    private String name;

    private String email;

    private String password;

    private String token;

    private String status;
}
