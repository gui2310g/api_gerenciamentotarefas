package com.example.gerenciamentoTarefas.domain.service;

import com.example.gerenciamentoTarefas.domain.enums.StatusTask;
import com.example.gerenciamentoTarefas.domain.exception.ResourceBadRequestException;
import com.example.gerenciamentoTarefas.domain.exception.ResourceNotFoundException;
import com.example.gerenciamentoTarefas.domain.model.Task;
import com.example.gerenciamentoTarefas.domain.repository.TaskRepository;
import com.example.gerenciamentoTarefas.dto.Task.TaskRequest;
import com.example.gerenciamentoTarefas.dto.Task.TaskResponse;
import com.example.gerenciamentoTarefas.mapper.TaskMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TaskService implements ICRUDService <TaskRequest, TaskResponse> {

    private final TaskRepository taskRepository;

    private final TaskMapper taskMapper;

    @Override
    public List<TaskResponse> findAll() {
        if(taskRepository.findAll().isEmpty()) throw new ResourceNotFoundException("Não há tasks");

        return taskRepository.findAll().stream().map(taskMapper::toDto).toList();
    }

    @Override
    public TaskResponse findById(Long id) {
        return taskRepository.findById(id).map(taskMapper::toDto)
                .orElseThrow(() -> new ResourceNotFoundException("Não foi encontrado task com id " + id));
    }

    @Override
    public TaskResponse create(TaskRequest dto) {
        Task task = taskMapper.toRequest(dto);
        return taskMapper.toDto(taskRepository.save(task));
    }

    @Override
    public TaskResponse update(Long id, TaskRequest dto) {
        findById(id);

        if(dto.getStatus() == StatusTask.PENDENTE || dto.getStatus() == StatusTask.CONCLUIDA)
            throw new ResourceBadRequestException("Somente em Andamento");

        Task task = taskMapper.toRequest(dto);
        task.setId(id);
        task.setTitle(dto.getTitle());
        task.setStatus(dto.getStatus());
        task.setDescription(dto.getDescription());
        return taskMapper.toDto(taskRepository.save(task));
    }

    @Override
    public void delete(Long id) {
        Optional<Task> task = taskRepository.findById(id);

        if(task.isEmpty()) throw new ResourceNotFoundException("Não foi encontrado task com id " + id)

        Task task1 = task.get();
        task1.setStatus(StatusTask.CONCLUIDA);
        taskRepository.save(task1);
    }
}
