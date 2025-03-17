package com.example.gerenciamentoTarefas.mapper;

import com.example.gerenciamentoTarefas.domain.model.User;
import com.example.gerenciamentoTarefas.dto.User.UserDetailsResponse;
import com.example.gerenciamentoTarefas.dto.User.UserRequest;
import com.example.gerenciamentoTarefas.dto.User.UserResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {
    @Mapping(target = "status", source = "status")
    UserResponse toDto(User user);

    @Mapping(target = "status", source = "status")
    UserDetailsResponse toDetailDto(User user);

    User toRequest(UserRequest userRequest);
}
