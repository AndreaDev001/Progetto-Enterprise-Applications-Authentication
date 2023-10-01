package com.enterpriseapplications.authserver.controllers.users;


import com.enterpriseapplications.authserver.data.dto.output.users.GoogleUserDto;
import com.enterpriseapplications.authserver.service.interfaces.users.GoogleUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/googleUsers")
@RequiredArgsConstructor
public class GoogleUserController
{
    private final GoogleUserService googleUserService;

    @GetMapping("id/{userID}")
    public ResponseEntity<GoogleUserDto> getUser(@PathVariable("userID")UUID id) {
        return ResponseEntity.ok(this.googleUserService.getUser(id));
    }

    @GetMapping("username/{username}")
    public ResponseEntity<GoogleUserDto> getUser(@PathVariable("username") String username) {
        return ResponseEntity.ok(this.googleUserService.getUser(username));
    }
}
