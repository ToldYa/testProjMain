package com.vesa.testprojmain.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
public class MyController {

    @RequestMapping("/")
    public String index() {
        return "Greetings from Spring Boot!";
    }

}