package com.enterpriseapplications.authserver.controllers.users;

import com.enterpriseapplications.authserver.data.dto.input.CreateLocalUserDto;
import com.enterpriseapplications.authserver.data.dto.output.users.LocalUserDto;
import com.enterpriseapplications.authserver.service.interfaces.users.LocalUserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@Controller
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
    @SneakyThrows
    public void createUser(@ParameterObject CreateLocalUserDto createLocalUserDto, HttpServletRequest httpServletRequest,HttpServletResponse response) {
        this.userService.createUser(createLocalUserDto);
        response.sendRedirect("http://localhost:9000/login");
    }
}
