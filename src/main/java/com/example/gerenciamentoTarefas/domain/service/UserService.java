package com.example.gerenciamentoTarefas.domain.service;

import com.example.gerenciamentoTarefas.domain.enums.UserRoles;
import com.example.gerenciamentoTarefas.domain.exception.ResourceBadRequestException;
import com.example.gerenciamentoTarefas.domain.exception.ResourceNotFoundException;
import com.example.gerenciamentoTarefas.domain.model.User;
import com.example.gerenciamentoTarefas.domain.repository.UserRepository;
import com.example.gerenciamentoTarefas.dto.User.UserDetailsResponse;
import com.example.gerenciamentoTarefas.dto.User.UserRequest;
import com.example.gerenciamentoTarefas.dto.User.UserResponse;
import com.example.gerenciamentoTarefas.mapper.UserMapper;
import com.example.gerenciamentoTarefas.security.TokenService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UserService {

    private UserRepository userRepository;

    private UserMapper userMapper;

    private PasswordEncoder passwordEncoder;

    private TokenService tokenService;

    public UserDetailsResponse create(UserRequest dto) {
        if (userRepository.findByEmail(dto.getEmail()).isPresent())
            throw new ResourceBadRequestException("E-mail já cadastrado!"); 

        User user = userMapper.toRequest(dto);
        user.setPassword(passwordEncoder.encode(dto.getPassword()));
        String token = tokenService.generateToken(user.getEmail(), user.getStatus().name());
        UserDetailsResponse response = userMapper.toDetailDto(userRepository.save(user));
        response.setToken(token);

        return response;
    }

    public List<UserResponse> findAll() {
        if(userRepository.findAll().isEmpty()) throw new ResourceNotFoundException("Não há usuario existente");
        return userRepository.findAll().stream()
                .filter(p -> p.getStatus().equals(UserRoles.USER)).map(userMapper::toDto).toList();
    }

    public UserResponse findById(Long id) {
        return userRepository.findById(id).map(userMapper::toDto)
                .orElseThrow(() -> new ResourceNotFoundException("Não foi possivel achar usuario com id " + id));
    }

    public UserResponse update(Long id, UserRequest dto) {
        findById(id);

        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if(user.getStatus().equals(UserRoles.ADMIN)) throw new ResourceBadRequestException("O admin não pode atualizar");

        user.setId(id);
        user.setName(dto.getName());
        user.setEmail(dto.getEmail());
        user.setPassword(dto.getPassword());
        user.setPassword(passwordEncoder.encode(dto.getPassword()));

        return userMapper.toDto(userRepository.save(user));
    }

    public void delete(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Não foi possivel achar usuario com id " + id));

        if(user.getStatus().equals(UserRoles.ADMIN)) throw new ResourceBadRequestException("O admin não pode se apagar");

        userRepository.deleteById(id);
    }
}
