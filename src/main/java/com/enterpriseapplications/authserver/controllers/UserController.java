package com.enterpriseapplications.authserver.controllers;

import com.enterpriseapplications.authserver.data.dto.input.CreateUserDto;
import com.enterpriseapplications.authserver.data.dto.output.UserDto;
import com.enterpriseapplications.authserver.service.interfaces.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/id/{userID}")
    public ResponseEntity<UserDto> getUser(@PathVariable("userID") UUID id) {
        return ResponseEntity.ok(this.userService.getUserByID(id));
    }

    @GetMapping("/username/{username}")
    public ResponseEntity<UserDto> getUser(@PathVariable("username") String username) {
        return ResponseEntity.ok(this.userService.getUserByUsername(username));
    }

    @PostMapping("/create")
    public ResponseEntity<UserDto> createUser(@Valid @RequestBody CreateUserDto createUserDto) {
        return ResponseEntity.ok(this.userService.createUser(createUserDto));
    }
}
