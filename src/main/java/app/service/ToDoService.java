package app.service;

import app.entity.ToDoEntity;
import app.repository.ToDoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ToDoService {
    @Autowired
    ToDoRepository toDoRepository;

    public void save(String todo, String personInCharge) {
        ToDoEntity entity = new ToDoEntity();
        entity.setTodo(todo);
        entity.setPersonInCharge(personInCharge);
        entity.setFinished(false);
        save(entity);
    }

    public void save(ToDoEntity entity) {
        toDoRepository.save(entity);
    }

    public List<ToDoEntity> findAll() {
        return toDoRepository.findAll();
    }
}
