package com.spring.sample.controller;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@EnableAutoConfiguration
public class SignUpController {
    @GetMapping("/sign_up")
    public ModelAndView index(
            ModelAndView mav
    ) {
        mav.setViewName("sign_up.html");
        return mav;
    }
}
