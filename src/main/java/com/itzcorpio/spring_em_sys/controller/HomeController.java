package com.itzcorpio.spring_em_sys.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://localhost:3175")
@RestController
@RequestMapping("/")
public class HomeController {

    @GetMapping
    public String home() {
        return "Hello API __________ Login-(http://localhost:8083/auth/login), Register-(http://localhost:8083/auth/register)";
    }

}
