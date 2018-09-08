package com.spring.sample.service;

import com.spring.sample.Exception.NotFoundException;
import com.spring.sample.entity.ToDoEntity;
import com.spring.sample.repository.ToDoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ToDoService {
    @Autowired
    ToDoRepository toDoRepository;

    public ToDoEntity save(String todo, String personInCharge) {
        ToDoEntity entity = ToDoEntity.builder()
                .todo(todo)
                .personInCharge(personInCharge)
                .isFinished(false)
                .build();
        return save(entity);
    }

    public ToDoEntity save(ToDoEntity entity) {
         return toDoRepository.save(entity);
    }

    public List<ToDoEntity> findAll() {
        return toDoRepository.findAll();
    }

    public ToDoEntity findById(int id) {
        return toDoRepository.findById(id).orElseThrow(NotFoundException::new);
    }
}
