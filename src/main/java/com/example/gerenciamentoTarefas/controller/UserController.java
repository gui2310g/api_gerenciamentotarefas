package com.example.gerenciamentoTarefas.controller;

import com.example.gerenciamentoTarefas.domain.service.UserService;
import com.example.gerenciamentoTarefas.dto.User.UserDetailsResponse;
import com.example.gerenciamentoTarefas.dto.User.UserRequest;
import com.example.gerenciamentoTarefas.dto.User.UserResponse;
import com.example.gerenciamentoTarefas.security.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuarios")
@AllArgsConstructor
public class UserController {

    private UserService userService;

    private AuthService authService;

    @PostMapping
    public ResponseEntity<UserDetailsResponse> create(@RequestBody UserRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.create(request));
    }

    @GetMapping
    public ResponseEntity<List<UserResponse>> findAll() {
        return ResponseEntity.ok(userService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> findById(@PathVariable Long id) {
        return ResponseEntity.ok(userService.findById(id));
    }

    public ResponseEntity<UserResponse> update(Authentication authentication, @RequestBody UserRequest request) {
        return ResponseEntity.ok(userService.update(authService.getAuthenticatedUserId(authentication), request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        userService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
