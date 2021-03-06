package com.spring.sample.controller;

import com.spring.sample.entity.ToDoEntity;
import com.spring.sample.service.ToDoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@EnableAutoConfiguration
public class ToDoController extends ExceptionHandleController
{
    @Autowired
    ToDoService toDoService;

    @GetMapping("/")
    public ModelAndView index(ModelAndView mav) {
        mav.setViewName("index.html");
        List<ToDoEntity> entityList = toDoService.findAll();
        mav.addObject("todo_list", entityList);
        return mav;
    }

    @PostMapping("/")
    public ModelAndView post(
            ModelAndView mav,
            @RequestParam("todo") String todo,
            @RequestParam("personInCharge") String personInCharge
    ) {
        toDoService.save(todo, personInCharge);
        mav.setViewName("index.html");
        List<ToDoEntity> entityList = toDoService.findAll();
        mav.addObject("todo_list", entityList);
        return mav;
    }

    @GetMapping("/{id}")
    public ModelAndView detail(@PathVariable int id, ModelAndView mav) {
        mav.setViewName("detail.html");
        ToDoEntity entity = toDoService.findById(id);
        mav.addObject("todo", entity);
        return mav;
    }
}
