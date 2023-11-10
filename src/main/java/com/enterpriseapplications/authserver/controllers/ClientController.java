package com.enterpriseapplications.authserver.controllers;


import com.enterpriseapplications.authserver.data.dao.ClientDao;
import com.enterpriseapplications.authserver.data.dto.input.CreateClientDto;
import com.enterpriseapplications.authserver.data.dto.output.ClientDto;
import com.enterpriseapplications.authserver.service.interfaces.ClientService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/clients")
@RequiredArgsConstructor
public class ClientController
{
    private final ClientService clientService;

    @GetMapping("/private/id/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<ClientDto> getClient(@PathVariable("id")UUID id) {
        return ResponseEntity.ok(this.clientService.getClient(id));
    }

    @GetMapping("/private/clientID/{clientID}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<ClientDto> getClient(@PathVariable("clientID") String clientID) {
        return ResponseEntity.ok(this.clientService.getClient(clientID));
    }

    @PostMapping("/private/create")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<ClientDto> createClient(@RequestBody @Valid CreateClientDto createClientDto) {
        return ResponseEntity.ok(this.clientService.createClient(createClientDto));
    }
}
