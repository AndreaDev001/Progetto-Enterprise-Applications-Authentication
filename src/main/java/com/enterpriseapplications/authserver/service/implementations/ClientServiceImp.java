package com.enterpriseapplications.authserver.service.implementations;


import com.enterpriseapplications.authserver.data.dao.ClientDao;
import com.enterpriseapplications.authserver.data.dto.input.CreateClientDto;
import com.enterpriseapplications.authserver.data.dto.output.ClientDto;
import com.enterpriseapplications.authserver.data.entities.Client;
import com.enterpriseapplications.authserver.service.interfaces.ClientService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClientRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ClientServiceImp implements ClientService, RegisteredClientRepository
{
    private final ClientDao clientDao;
    private final PasswordEncoder passwordEncoder;
    private final ModelMapper modelMapper;

    @Override
    public ClientDto getClient(UUID id) {
        Client client = this.clientDao.findById(id).orElseThrow();
        return this.modelMapper.map(client,ClientDto.class);
    }

    @Override
    public ClientDto getClient(String clientID) {
        Client client = this.clientDao.getByClientID(clientID).orElseThrow();
        return this.modelMapper.map(client,ClientDto.class);
    }

    @Override
    @Transactional
    public ClientDto createClient(CreateClientDto createClientDto) {
        Client client = new Client();
        client.setClientID(createClientDto.getClientID());
        client.setClientSecret(passwordEncoder.encode(createClientDto.getClientSecret()));
        client.setAuthenticationMethods(createClientDto.getAuthenticationMethods());
        client.setAuthorizationGrantTypes(createClientDto.getAuthorizationGrantTypes());
        client.setRedirectUris(createClientDto.getRedirectUris());
        client.setScopes(createClientDto.getScopes());
        client.setProofKey(createClientDto.isProofKey());
        this.clientDao.save(client);
        return this.modelMapper.map(client,ClientDto.class);
    }

    @Override
    public void save(RegisteredClient registeredClient) {

    }

    @Override
    public RegisteredClient findById(String id) {
        Client client = this.clientDao.findById(UUID.fromString(id)).orElseThrow();
        return Client.convert(client);
    }

    @Override
    public RegisteredClient findByClientId(String clientId) {
        Client client = this.clientDao.getByClientID(clientId).orElseThrow();
        return Client.convert(client);
    }
}
