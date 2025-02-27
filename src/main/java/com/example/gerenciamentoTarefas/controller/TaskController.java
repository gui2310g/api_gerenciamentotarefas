package com.example.gerenciamentoTarefas.controller;

import com.example.gerenciamentoTarefas.domain.service.TaskService;
import com.example.gerenciamentoTarefas.dto.Task.TaskRequest;
import com.example.gerenciamentoTarefas.dto.Task.TaskResponse;
import com.example.gerenciamentoTarefas.security.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tasks")
@AllArgsConstructor
public class TaskController implements ICrudController <TaskRequest, TaskResponse> {

    private TaskService taskService;

    private AuthService authService;

    @Override
    @PostMapping
    public ResponseEntity<TaskResponse> create(@RequestBody TaskRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(taskService.create(request));
    }

    @Override
    @GetMapping
    public ResponseEntity<List<TaskResponse>> findAll() {
        return ResponseEntity.ok(taskService.findAll());
    }

    @GetMapping("/findByAuth")
    public ResponseEntity<List<TaskResponse>> findAlLByUserLogged(Authentication authentication) {
        return ResponseEntity.ok(taskService.findAlLByUserLogged(authService.getAuthenticatedUserId(authentication)));
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<TaskResponse> findById(@PathVariable Long id) {
        return ResponseEntity.ok(taskService.findById(id));
    }


    @PutMapping("/{id}")
    public ResponseEntity<TaskResponse> update(@PathVariable Long id, @RequestBody TaskRequest request) {
        return ResponseEntity.ok(taskService.update(id, request));
    }

    @Override
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        taskService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
