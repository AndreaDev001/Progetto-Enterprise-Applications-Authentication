package com.enterpriseapplications.authserver.data.dto.output.users;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDate;


@EqualsAndHashCode(callSuper = false)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LocalUserDto extends UserDto
{
    private boolean expired;
    private boolean locked;
    private boolean enabled;
    private LocalDate createdDate;
}
