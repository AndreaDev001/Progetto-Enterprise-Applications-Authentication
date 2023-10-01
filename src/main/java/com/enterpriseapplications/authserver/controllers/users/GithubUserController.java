package com.enterpriseapplications.authserver.controllers.users;


import com.enterpriseapplications.authserver.data.dto.output.users.GithubUserDto;
import com.enterpriseapplications.authserver.service.interfaces.users.GithubUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/githubUsers")
@RequiredArgsConstructor
public class GithubUserController
{
    private final GithubUserService githubUserService;

    @GetMapping("id/{userID}")
    public ResponseEntity<GithubUserDto> getUser(@PathVariable("userID")UUID id) {
        return ResponseEntity.ok(this.githubUserService.getUser(id));
    }

    @GetMapping("username/{username}")
    public ResponseEntity<GithubUserDto> getUser(@PathVariable("username") String username) {
        return ResponseEntity.ok(this.githubUserService.getUser(username));
    }
}
