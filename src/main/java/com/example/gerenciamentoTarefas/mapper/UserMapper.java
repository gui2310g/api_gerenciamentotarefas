package com.example.gerenciamentoTarefas.mapper;

import com.example.gerenciamentoTarefas.domain.model.User;
import com.example.gerenciamentoTarefas.dto.User.UserRequest;
import com.example.gerenciamentoTarefas.dto.User.UserResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserResponse toDto(User user);

    User toRequest(UserRequest userRequest);
}
