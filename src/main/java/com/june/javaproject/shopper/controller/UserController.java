package com.june.javaproject.shopper.controller;

import com.june.javaproject.shopper.entity.User;
import com.june.javaproject.shopper.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<User> register(@RequestBody User user) {
        return ResponseEntity.ok(userService.register(user));
    }

    @GetMapping("/{username}")
    public ResponseEntity<User> findByUsername(@PathVariable String username) {
        return userService.findByUsername(username)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/{userId}/role")
    public ResponseEntity<String> setRole(@PathVariable long userId, @RequestBody Map<String, String> request) {
        String role = request.get("role");
        userService.setUserRole(userId, role);
        return ResponseEntity.ok("角色更新成功");
    }
}
