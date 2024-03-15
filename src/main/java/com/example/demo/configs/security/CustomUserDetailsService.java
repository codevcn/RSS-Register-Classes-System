package com.example.demo.configs.security;

import com.example.demo.models.Account;
import com.example.demo.repositories.AccountRepository;
import com.example.demo.utils.messages.AuthMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private AccountRepository accountRepository;

    @Override
    public CustomUserDetails loadUserByUsername(String studentUsername)
        throws UsernameNotFoundException {
        Account studentAccount = accountRepository.findByUsername(studentUsername);
        if (studentAccount == null) {
            throw new UsernameNotFoundException(AuthMessage.USER_NOT_FOUND);
        }
        return new CustomUserDetails(studentAccount);
    }
}
