package com.enterpriseapplications.authserver.data.entities.users;


import com.enterpriseapplications.authserver.data.entities.Role;
import com.enterpriseapplications.authserver.data.entities.enums.Provider;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;


@EqualsAndHashCode(callSuper = false)
@Entity
@Table(name = "LOCAL_USERS")
@EntityListeners({AuditingEntityListener.class})
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LocalUser extends User implements UserDetails
{
    @Column(name = "PASSWORD",unique = false,nullable = false)
    private String password;

    @Column(name = "NAME",unique = false,nullable = false)
    @Length(min = 3,max = 10)
    private String name;

    @Column(name = "SURNAME",unique = false,nullable = false)
    @Length(min = 3,max = 10)
    private String surname;

    @Column(name = "EXPIRED",unique = false,nullable = false)
    private boolean expired;

    @Column(name = "LOCKED",unique = false,nullable = false)
    private boolean locked;

    @Column(name = "ENABLED",unique = false,nullable = false)
    private boolean enabled;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.getRoles();
    }

    @Override
    public boolean isAccountNonExpired() {
        return !this.expired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return !this.locked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return this.enabled;
    }
}
