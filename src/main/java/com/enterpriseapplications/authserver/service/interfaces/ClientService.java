package com.enterpriseapplications.authserver.service.interfaces;

import com.enterpriseapplications.authserver.data.dto.input.CreateClientDto;
import com.enterpriseapplications.authserver.data.dto.output.ClientDto;

import java.util.UUID;

public interface ClientService
{
    ClientDto getClient(UUID id);
    ClientDto getClient(String clientID);
    ClientDto createClient(CreateClientDto createClientDto);
}
