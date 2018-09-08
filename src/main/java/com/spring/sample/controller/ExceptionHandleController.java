package com.spring.sample.controller;

import com.spring.sample.Exception.NotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Controller
public class ExceptionHandleController {

    @ExceptionHandler(NotFoundException.class)
    String handleNotFoundException(NotFoundException e) {
        return "error.html";
    }
}
