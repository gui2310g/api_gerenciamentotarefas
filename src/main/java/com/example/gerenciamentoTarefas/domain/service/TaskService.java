package com.example.gerenciamentoTarefas.domain.service;

import com.example.gerenciamentoTarefas.domain.enums.StatusTask;
import com.example.gerenciamentoTarefas.domain.enums.UserRoles;
import com.example.gerenciamentoTarefas.domain.exception.ResourceBadRequestException;
import com.example.gerenciamentoTarefas.domain.exception.ResourceNotFoundException;
import com.example.gerenciamentoTarefas.domain.model.Task;
import com.example.gerenciamentoTarefas.domain.model.User;
import com.example.gerenciamentoTarefas.domain.repository.TaskRepository;
import com.example.gerenciamentoTarefas.dto.Task.TaskRequest;
import com.example.gerenciamentoTarefas.dto.Task.TaskResponse;
import com.example.gerenciamentoTarefas.mapper.TaskMapper;
import lombok.AllArgsConstructor;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class TaskService implements ICRUDService <TaskRequest, TaskResponse> {

    private TaskRepository taskRepository;

    private TaskMapper taskMapper;

    @Override
    public List<TaskResponse> findAll() {
        if(taskRepository.findAll().isEmpty()) throw new ResourceNotFoundException("Não há tasks");
        return taskRepository.findAll().stream().map(taskMapper::toDto).toList();
    }

    public List<TaskResponse> findAlLByUserLogged(Long id) {
        if(taskRepository.findAllByUserId(id).isEmpty())
            throw new ResourceNotFoundException("Não há tasks para este usuário");

        return taskRepository.findAllByUserId(id).stream().map(taskMapper::toDto).toList();
    }

    @Override
    public TaskResponse findById(Long id) {
        return taskRepository.findById(id).map(taskMapper::toDto)
                .orElseThrow(() -> new ResourceNotFoundException("Não foi encontrado task com id " + id));
    }

    @Override
    public TaskResponse create(TaskRequest dto) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if(user.getStatus().equals(UserRoles.ADMIN)) throw new ResourceBadRequestException("O admin não pode criar");

        Task task = taskMapper.toRequest(dto);

        if(task.getStatus().equals(StatusTask.CONCLUIDA))
            throw new ResourceBadRequestException("Não se pode criar como concluido");

        task.setDueDate(new Date());
        task.setUser(user);

        return taskMapper.toDto(taskRepository.save(task));
    }

    @Override
    public TaskResponse update(Long id, TaskRequest dto) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Não foi encontrado task com id " + id));

        if(task.getStatus().equals(StatusTask.CONCLUIDA)) throw new ResourceBadRequestException("CONCLUIDA negado");

        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (!task.getUser().getId().equals(user.getId()))
            throw new AccessDeniedException("Você não tem permissão para modificar esta tarefa.");

        task.setId(id);
        task.setTitle(dto.getTitle());
        task.setDescription(dto.getDescription());
        task.setDueDate(new Date());
        task.setStatus(dto.getStatus());
        task.setUser(user);

        return taskMapper.toDto(taskRepository.save(task));
    }

    @Override
    public void delete(Long id) {
        Optional<Task> task = taskRepository.findById(id);
        if(task.isEmpty()) throw new ResourceNotFoundException("Não foi encontrado task com id " + id);

        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Task task1 = task.get();

        if (!task1.getUser().getId().equals(user.getId()))
            throw new AccessDeniedException("Você não tem permissão para deletar esta tarefa.");

        task1.setStatus(StatusTask.CONCLUIDA);
        task1.setUser(user);

        taskRepository.save(task1);
    }
}
