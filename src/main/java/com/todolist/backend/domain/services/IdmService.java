package com.todolist.backend.domain.services;

import com.todolist.backend.application.dtos.idm.signup.SignUpRequest;
import com.todolist.backend.application.dtos.idm.signup.SignUpResponse;
import com.todolist.backend.domain.model.SysAuthentication;
import com.todolist.backend.domain.model.SysUser;
import com.todolist.backend.domain.services.security.idm.SysUserDetailsService;
import com.todolist.backend.domain.services.security.jwt.JwtService;
import com.todolist.backend.persistence.repository.SysUserRepository;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.CredentialsExpiredException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.security.auth.login.CredentialNotFoundException;
import javax.validation.Valid;

@Service
@RequiredArgsConstructor
@Data

public class IdmService {
    private final SysUserRepository sysUserRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager  authenticationManager;
    private final SysUserDetailsService sysUserDetailsService;
    private final JwtService jwtService;

    public SysUser signUp(SysUser user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return sysUserRepository.save(user);
    }

    public SysAuthentication login(SysAuthentication sysAuthentication) {
        Authentication authentication = getAuthenticationStatus(sysAuthentication);
        if(authentication.isAuthenticated()) {
            return buildSysAuthentication(sysAuthentication);
        }

        throw new CredentialsExpiredException("Invaild Credentials");

    }

    private SysAuthentication buildSysAuthentication(SysAuthentication sysAuthentication) {
        UserDetails userDetails = sysUserDetailsService.loadUserByUsername(sysAuthentication.getUsername());
        String token = jwtService.generateToken(userDetails);
        sysAuthentication.setToken(token);
        return sysAuthentication;
    }

    private Authentication getAuthenticationStatus(SysAuthentication sysAuthentication) {
        return authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        sysAuthentication.getUsername(),
                        sysAuthentication.getPassword()
                )
        );
    }
}
