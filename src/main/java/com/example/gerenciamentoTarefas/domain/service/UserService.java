package com.example.gerenciamentoTarefas.domain.service;

import com.example.gerenciamentoTarefas.domain.model.User;
import com.example.gerenciamentoTarefas.domain.repository.UserRepository;
import com.example.gerenciamentoTarefas.dto.User.UserRequest;
import com.example.gerenciamentoTarefas.dto.User.UserResponse;
import com.example.gerenciamentoTarefas.mapper.UserMapper;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService implements ICRUDService<UserRequest, UserResponse> {

    private final UserRepository userRepository;

    private UserMapper userMapper;

    @Override
    public UserResponse create(UserRequest dto) throws RuntimeException {
        if (userRepository.findByEmail(dto.getEmail()).isPresent()) throw new RuntimeException("E-mail já cadastrado!");

        User user = userMapper.toRequest(dto);
        return userMapper.toDto(userRepository.save(user));
    }

    @Override
    public List<UserResponse> findAll() {
        return userRepository.findAll().stream().map(userMapper::toDto).toList();
    }

    @Override
    public UserResponse findById(Long id) {
        return userRepository.findById(id).map(userMapper::toDto)
                .orElseThrow(() -> new ResourceNotFoundException("Não foi possivel achar usuario com id " + id));
    }

    @Override
    public UserResponse update(Long id, UserRequest dto) {
        User user = userMapper.toRequest(dto);
        user.setName(dto.getName());
        user.setEmail(dto.getEmail());
        user.setPassword(dto.getPassword());

        return userMapper.toDto(userRepository.save(user));
    }

    @Override
    public void delete(Long id) {
        userRepository.findById(id);
        userRepository.deleteById(id);
    }
}
