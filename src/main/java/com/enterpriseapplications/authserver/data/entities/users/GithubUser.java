package com.enterpriseapplications.authserver.data.entities.users;


import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "GITHUB_USERS")
@EqualsAndHashCode(callSuper = false)
@Data
public class GithubUser extends User
{

}
