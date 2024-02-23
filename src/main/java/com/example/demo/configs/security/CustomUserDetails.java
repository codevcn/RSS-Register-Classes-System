package com.example.demo.configs.security;

import com.example.demo.models.Student;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@ToString
public class CustomUserDetails implements UserDetails {

    private String fullName;
    private String email;
    private String password;
    private List<GrantedAuthority> authorities;

    public CustomUserDetails(Student student) {
        this.fullName = student.getFullName();
        this.email = student.getId();
        this.password = student.getAccount().getPassword();
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(student.getAccount().getRole().getId().toUpperCase()));
        this.authorities = authorities;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
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

    public String getFullName() {
        return fullName;
    }
}
