package com.example.demo.controller;

import com.example.demo.request.UserRequest;
import com.example.demo.response.UserResponse;
import com.example.demo.service.UserService;
import lombok.val;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{id}")
    public UserResponse find(@PathVariable Long id) {
        val user = userService.findById(id);
        return new UserResponse(user.getId(), user.getName());
    }

    @GetMapping("/all")
    List<UserResponse> list() {
        val users = userService.findAll().stream().map(user ->
                        new UserResponse(user.getId(), user.getName()))
                .collect(Collectors.toList());
        return users;
    }

    @PostMapping
    UserResponse insert(@RequestBody UserRequest userRequest) {
        val saved = userService.insert(userRequest.getName());
        return new UserResponse(saved.getId(), saved.getName());
    }
}
