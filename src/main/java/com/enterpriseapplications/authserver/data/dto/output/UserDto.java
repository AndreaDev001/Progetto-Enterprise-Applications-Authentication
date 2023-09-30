package com.enterpriseapplications.authserver.data.dto.output;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto
{
    private UUID id;
    private String email;
    private String username;
    private boolean expired;
    private boolean locked;
    private boolean enabled;
    private LocalDate createdDate;
}
