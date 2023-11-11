package com.enterpriseapplications.authserver.data.entities;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

import java.util.UUID;

@Entity
@Table(name = "ROLES")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Role implements GrantedAuthority
{
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "ROLE_ID")
    private UUID id;

    @Column(name = "NAME",unique = true,nullable = false)
    private String name;

    @Override
    public String getAuthority() {
        return this.name;
    }
}
