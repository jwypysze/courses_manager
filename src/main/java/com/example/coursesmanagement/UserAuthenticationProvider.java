package com.example.coursesmanagement;

import com.example.coursesmanagement.model.entity.UserEntity;
import com.example.coursesmanagement.model.enums.ActiveUser;
import com.example.coursesmanagement.model.enums.UserRole;
import com.example.coursesmanagement.repository.UserJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class UserAuthenticationProvider implements AuthenticationProvider {

    private final UserJpaRepository userJpaRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();
        String password = authentication.getCredentials().toString();
        UserEntity userByName = userJpaRepository.findByLogin(username);
        if (userByName == null || userByName.getActiveUser() == ActiveUser.NO) {
            throw new IllegalArgumentException("User not found or user is not active");
        }
        String encodePassword = passwordEncoder.encode(userByName.getPassword());
        if (passwordEncoder.matches(password, encodePassword)) {
            UserRole userRole = userByName.getUserRole();
            List<GrantedAuthority> authorities = List.of(new SimpleGrantedAuthority(userRole.name()));
            return new UsernamePasswordAuthenticationToken(username, password, authorities);
        } else {
            throw new IllegalArgumentException("Password mismatch!");
        }
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
