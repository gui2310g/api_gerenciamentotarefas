package com.example.gerenciamentoTarefas.mapper;

import com.example.gerenciamentoTarefas.domain.model.Task;
import com.example.gerenciamentoTarefas.dto.Task.TaskRequest;
import com.example.gerenciamentoTarefas.dto.Task.TaskResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface TaskMapper {
    @Mapping(target = "userId", source = "user.id")
    TaskResponse toDto(Task task);

    Task toRequest(TaskRequest taskRequest);
}
