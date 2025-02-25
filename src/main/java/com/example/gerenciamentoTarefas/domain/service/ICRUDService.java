package com.example.gerenciamentoTarefas.domain.service;

import java.util.List;

public interface ICRUDService <Req, Res>{
    List<Res> findAll();
    Res findById(Long id);
    Res create(Req dto);
    Res update(Long id, Req dto);
    void delete(Long id);
}
