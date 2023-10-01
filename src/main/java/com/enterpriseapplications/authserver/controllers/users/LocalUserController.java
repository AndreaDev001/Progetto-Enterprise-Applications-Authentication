package com.enterpriseapplications.authserver.controllers.users;

import com.enterpriseapplications.authserver.data.dto.input.CreateLocalUserDto;
import com.enterpriseapplications.authserver.data.dto.output.users.LocalUserDto;
import com.enterpriseapplications.authserver.service.interfaces.users.LocalUserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/localUsers")
@RequiredArgsConstructor
public class LocalUserController {

    private final LocalUserService userService;

    @GetMapping("/id/{userID}")
    public ResponseEntity<LocalUserDto> getUser(@PathVariable("userID") UUID id) {
        return ResponseEntity.ok(this.userService.getUser(id));
    }

    @GetMapping("/username/{username}")
    public ResponseEntity<LocalUserDto> getUser(@PathVariable("username") String username) {
        return ResponseEntity.ok(this.userService.getUser(username));
    }

    @PostMapping("/create")
    public ResponseEntity<LocalUserDto> createUser(@RequestBody CreateLocalUserDto createUserDto) {
        System.out.println("CALLED");
        return ResponseEntity.ok(this.userService.createUser(createUserDto));
    }
}
