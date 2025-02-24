package com.example.gerenciamentoTarefas.domain.model;

import com.example.gerenciamentoTarefas.domain.enums.StatusTask;
import com.example.gerenciamentoTarefas.dto.Task.TaskRequest;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Data
@Entity
@Table(name = "tasks")
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String description;

    @Enumerated(EnumType.STRING)
    private StatusTask status = StatusTask.PENDENTE;

    private Date dueDate;

    @ManyToOne
    @JoinColumn(name = "userId")
    private User user;

    public Task(TaskRequest dto) {
        this.title = dto.getTitle();
        this.description = dto.getDescription();
    }
}
