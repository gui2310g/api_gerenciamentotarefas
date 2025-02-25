package com.example.gerenciamentoTarefas.controller;

import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ICrudController <Req, Res>{
    ResponseEntity<Res> create(Req request);
    ResponseEntity<List<Res>> findAll();
    ResponseEntity<Res> findById(Long id);
    ResponseEntity<Res> update(Long id, Req request);
    ResponseEntity<?> delete(Long id);
}
