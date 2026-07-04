package com.katlego.taskflow.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.katlego.taskflow.entity.User;
import com.katlego.taskflow.repository.UserRepository;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

    private final UserRepository repository;

    public AdminController(UserRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/users")
    public List<User> getUsers() {
        return repository.findAll();
    }
}
