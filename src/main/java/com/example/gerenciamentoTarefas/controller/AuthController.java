package com.example.gerenciamentoTarefas.controller;

import com.example.gerenciamentoTarefas.domain.model.User;
import com.example.gerenciamentoTarefas.dto.Login.LoginRequest;
import com.example.gerenciamentoTarefas.dto.Login.LoginResponse;
import com.example.gerenciamentoTarefas.security.TokenService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@AllArgsConstructor
public class AuthController {

    private AuthenticationManager authenticationManager;

    private TokenService tokenService;

    @PostMapping
    public ResponseEntity<LoginResponse> login(@RequestBody @Validated LoginRequest dto) {
        var usernamePassword = new UsernamePasswordAuthenticationToken(dto.getEmail(), dto.getPassword());
        var auth = authenticationManager.authenticate(usernamePassword);
        var token = tokenService.generateToken((User) auth.getPrincipal());

        return ResponseEntity.ok(new LoginResponse(token));
    }
}
