package com.enterpriseapplications.authserver.data.dto.output.users;


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
    protected UUID id;
    protected String email;
    protected String username;
    protected LocalDate createdDate;
}
