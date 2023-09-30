package com.enterpriseapplications.authserver.controllers;


import com.enterpriseapplications.authserver.data.dao.ClientDao;
import com.enterpriseapplications.authserver.data.dto.input.CreateClientDto;
import com.enterpriseapplications.authserver.data.dto.output.ClientDto;
import com.enterpriseapplications.authserver.service.interfaces.ClientService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/clients")
@RequiredArgsConstructor
public class ClientController
{
    private final ClientService clientService;

    @GetMapping("id/{id}")
    public ResponseEntity<ClientDto> getClient(@PathVariable("id")UUID id) {
        return ResponseEntity.ok(this.clientService.getClient(id));
    }

    @GetMapping("clientID/{clientID}")
    public ResponseEntity<ClientDto> getClient(@PathVariable("clientID") String clientID) {
        return ResponseEntity.ok(this.clientService.getClient(clientID));
    }

    @PostMapping("/create")
    public ResponseEntity<ClientDto> createClient(@RequestBody CreateClientDto createClientDto) {
        return ResponseEntity.ok(this.clientService.createClient(createClientDto));
    }
}
