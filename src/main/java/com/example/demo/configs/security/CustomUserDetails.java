package com.example.demo.configs.security;

import com.example.demo.models.Account;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@ToString
public class CustomUserDetails implements UserDetails {

    private String studentUsername;
    private String studentPassword;
    private List<GrantedAuthority> authorities;

    public CustomUserDetails(Account account) {
        this.studentUsername = account.getUsername();
        this.studentPassword = account.getPassword();
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(account.getRole().getRoleCode().toUpperCase()));
        this.authorities = authorities;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return studentPassword;
    }

    @Override
    public String getUsername() {
        return studentUsername;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
