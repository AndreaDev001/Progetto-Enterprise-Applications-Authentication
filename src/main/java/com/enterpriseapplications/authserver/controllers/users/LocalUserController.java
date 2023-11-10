package com.enterpriseapplications.authserver.controllers.users;

import com.enterpriseapplications.authserver.data.dto.input.CreateLocalUserDto;
import com.enterpriseapplications.authserver.data.dto.output.users.LocalUserDto;
import com.enterpriseapplications.authserver.service.interfaces.users.LocalUserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import jakarta.validation.Validator;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.net.BindException;
import java.util.UUID;

@RestController
@Controller
@RequestMapping("/localUsers")
@RequiredArgsConstructor
public class LocalUserController {

    private final LocalUserService userService;
    private final Validator validator;

    @GetMapping("/private/id/{userID}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<LocalUserDto> getUser(@PathVariable("userID") UUID id) {
        return ResponseEntity.ok(this.userService.getUser(id));
    }

    @GetMapping("/private/username/{username}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<LocalUserDto> getUser(@PathVariable("username") String username) {
        return ResponseEntity.ok(this.userService.getUser(username));
    }

    @PostMapping("/public/create")
    @SneakyThrows
    public void createUser(@ParameterObject @Valid CreateLocalUserDto createLocalUserDto, HttpServletRequest httpServletRequest,HttpServletResponse response) {
        try
        {
            this.userService.createUser(createLocalUserDto);
            response.sendRedirect("http://enterpriseapplications.live:9000/login");
        }
        catch (Exception exception) {
            response.sendRedirect("http://enterpriseapplications.live:9000/register?error=already_existing");
        }
    }
}
