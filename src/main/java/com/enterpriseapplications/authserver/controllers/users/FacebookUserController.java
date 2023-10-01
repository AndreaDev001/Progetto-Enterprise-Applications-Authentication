package com.enterpriseapplications.authserver.controllers.users;


import com.enterpriseapplications.authserver.data.dto.output.users.FacebookUserDto;
import com.enterpriseapplications.authserver.service.interfaces.users.FacebookUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/facebookUsers")
@RequiredArgsConstructor
public class FacebookUserController
{
    private final FacebookUserService facebookUserService;

    @GetMapping("id/{userID}")
    public ResponseEntity<FacebookUserDto> getUser(@PathVariable("userID") UUID id) {
        return ResponseEntity.ok(this.facebookUserService.getUser(id));
    }

    @GetMapping("username/{username}")
    public ResponseEntity<FacebookUserDto> getUser(@PathVariable("username") String username) {
        return ResponseEntity.ok(this.facebookUserService.getUser(username));
    }
}
