package com.enterpriseapplications.authserver.controllers.users;


import com.enterpriseapplications.authserver.data.dto.output.users.UserDto;
import com.enterpriseapplications.authserver.service.interfaces.users.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController
{
    private final UserService userService;

    @GetMapping("/private/id/{userID}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    private ResponseEntity<UserDto> getUser(@PathVariable("userID") UUID id) {
        return ResponseEntity.ok(this.userService.getUserByID(id));
    }

    @GetMapping("/private/username/{username}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    private ResponseEntity<UserDto> getUser(@PathVariable("username") String username) {
        return ResponseEntity.ok(this.userService.getUserByUsername(username));
    }
}
