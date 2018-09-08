package com.spring.sample.repository;

import com.spring.sample.entity.ToDoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ToDoRepository extends JpaRepository<ToDoEntity, Integer> {

}
