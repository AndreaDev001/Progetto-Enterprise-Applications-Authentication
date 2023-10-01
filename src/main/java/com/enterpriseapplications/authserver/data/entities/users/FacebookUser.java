package com.enterpriseapplications.authserver.data.entities.users;


import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "FACEBOOK_USERS")
@EqualsAndHashCode(callSuper = false)
@Data
public class FacebookUser extends User
{

}
