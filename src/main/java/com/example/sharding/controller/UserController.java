package com.example.sharding.controller;

import com.example.sharding.entity.UserEntity;
import com.example.sharding.service.UserShardingService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/users")
public class UserController {
    private final UserShardingService userService;

    public UserController(UserShardingService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<Void> createUser(@RequestParam String username) {
        userService.saveUser(username);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<UserEntity> fetchUser(@RequestParam String username) {
        return ResponseEntity.ok(userService.fetchUser(username));
    }
}
