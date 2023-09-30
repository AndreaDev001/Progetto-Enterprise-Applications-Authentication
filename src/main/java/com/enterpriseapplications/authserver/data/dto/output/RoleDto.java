package com.enterpriseapplications.authserver.data.dto.output;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoleDto
{
    private UUID id;
    private String name;
    private LocalDate createdDate;
}
