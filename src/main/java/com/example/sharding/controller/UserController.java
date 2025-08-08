package com.example.sharding.controller;

import com.example.sharding.service.UserShardingService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/users")
public class UserController {
    private final UserShardingService userService;

    public UserController(UserShardingService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<Void> createUser(@RequestBody String username) {
        userService.saveUser(username);
        return ResponseEntity.ok().build();
    }
}
