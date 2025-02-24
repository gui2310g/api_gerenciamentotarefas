package com.example.gerenciamentoTarefas.domain;

import com.example.gerenciamentoTarefas.domain.model.Task;
import com.example.gerenciamentoTarefas.domain.model.User;
import com.example.gerenciamentoTarefas.dto.Task.TaskRequest;
import com.example.gerenciamentoTarefas.dto.User.UserRequest;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
@ActiveProfiles("test")
class RepositoryTest {

    @Autowired
    EntityManager entityManager;

    @Autowired
    UserRepository userRepository;

    @Autowired
    TaskRepository taskRepository;

    @Test
    void findById() {
        UserRequest dto = new UserRequest(1L, "John Doe", "johndoe@example.com", "johndoe123");
        this.createUser(dto);

        Optional<User> foundUser = this.userRepository.findById(dto.getId());

        assertThat(foundUser.isPresent()).isTrue();
    }

    @Test
    void findByUserId() {
        UserRequest dto = new UserRequest(2L, "John Doe", "john@example.com", "johndoe123");
        User user = this.createUser(dto);

        TaskRequest task = new TaskRequest(1L, "Comprar", "Ir ao shopping comprar", user);
        this.createTask(task);

        List<Task> tasks = this.taskRepository.findByUserId(user.getId());

        assertThat(tasks).isNotNull();
    }

    private User createUser(UserRequest dto) {
        User user = new User(dto);
        this.entityManager.persist(user);
        return user;
    }

    private void createTask(TaskRequest dto) {
        Task task = new Task(dto);
        this.entityManager.persist(task);
    }
}