package com.example.gerenciamentoTarefas.domain.enums;

import lombok.Getter;

@Getter
public enum StatusTask {
    PENDENTE("PENDENTE"),
    CONCLUIDA("CONCLUIDO");

    private final String role;

    StatusTask(String role) { this.role = role; }
}
