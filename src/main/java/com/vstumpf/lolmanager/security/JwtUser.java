package com.vstumpf.lolmanager.security;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.vstumpf.lolmanager.model.RoleName;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Date;
import java.util.stream.Collectors;

/**
 * Created by Vincent on 7/24/2017.
 */

@AllArgsConstructor
public class JwtUser implements UserDetails {
    private final Long id;
    private final String username;
    @Getter private final String firstName;
    @Getter private final String lastName;
    private final String password;
    private final Collection<? extends GrantedAuthority> roles;
    private final boolean enabled;
    private final Date lastPasswordResetDate;

    @Override
    public String getUsername() {
        return username;
    }

    @JsonIgnore
    public Long getId() {
        return id;
    }

    @JsonIgnore
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @JsonIgnore
    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }

    @JsonIgnore
    public Date getLastPasswordResetDate() {
        return lastPasswordResetDate;
    }

    @JsonIgnore
    public boolean isAdmin() {
        return roles.stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList()).contains("ROLE_ADMIN");
    }
}
