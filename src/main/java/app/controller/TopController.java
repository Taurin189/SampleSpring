package app.controller;

import app.service.ToDoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@EnableAutoConfiguration
public class TopController
{
    @Autowired
    ToDoService toDoService;

    @GetMapping("/")
    public String index() {
        return "index.html";
    }

    @PostMapping("/")
    public String post(
            @RequestParam("todo") String todo,
            @RequestParam("personInCharge") String personInCharge
    ) {
        toDoService.save(todo, personInCharge);
        return "index.html";
    }
}
