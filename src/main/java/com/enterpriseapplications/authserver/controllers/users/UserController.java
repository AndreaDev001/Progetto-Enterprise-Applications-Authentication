package com.enterpriseapplications.authserver.controllers.users;


import com.enterpriseapplications.authserver.service.interfaces.users.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController
{
    private final UserService userService;
}
