package com.enterpriseapplications.authserver.data.entities.users;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;



@Entity
@Table(name = "GOOGLE_USERS")
@EqualsAndHashCode(callSuper = false)
@Data
public class GoogleUser extends User {


}
