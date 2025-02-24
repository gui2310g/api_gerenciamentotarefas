package com.example.gerenciamentoTarefas.dto.User;

import com.example.gerenciamentoTarefas.domain.enums.UserRoles;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserRequest {
    private Long id;

    private String name;

    private String email;

    private String password;
}
