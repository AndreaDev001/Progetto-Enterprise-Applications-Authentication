package com.enterpriseapplications.authserver.data.dto.input;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.ClientAuthenticationMethod;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateClientDto
{
    @NotNull
    @NotBlank
    private String clientID;

    @NotNull
    @NotBlank
    private String clientSecret;

    @NotNull
    @NotEmpty
    private Set<ClientAuthenticationMethod> authenticationMethods;

    @NotNull
    @NotEmpty
    private Set<AuthorizationGrantType> authorizationGrantTypes;

    @NotNull
    @NotEmpty
    private Set<String> redirectUris;

    @NotNull
    @NotEmpty
    private Set<String> scopes;

    @NotNull
    private boolean proofKey;
}
