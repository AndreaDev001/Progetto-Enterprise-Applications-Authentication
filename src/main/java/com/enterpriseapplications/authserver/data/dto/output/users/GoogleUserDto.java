package com.enterpriseapplications.authserver.data.dto.output.users;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = false)
@Data
public class GoogleUserDto extends UserDto
{
    private String externalID;
    private String name;
    private String givenName;
    private String familyName;
    private String pictureURL;
}
