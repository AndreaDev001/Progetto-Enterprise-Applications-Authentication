package com.enterpriseapplications.authserver.controllers.users;


import com.enterpriseapplications.authserver.service.interfaces.users.GithubUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users/github")
@RequiredArgsConstructor
public class GithubUserController
{
    private final GithubUserService githubUserService;
}
