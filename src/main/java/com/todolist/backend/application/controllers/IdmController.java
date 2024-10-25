package com.todolist.backend.application.controllers;

import com.todolist.backend.application.dtos.idm.login.SysAuthenticationRequest;
import com.todolist.backend.application.dtos.idm.login.SysAuthenticationResponse;
import com.todolist.backend.application.dtos.idm.signup.SignUpRequest;
import com.todolist.backend.application.dtos.idm.signup.SignUpResponse;
import com.todolist.backend.domain.mappers.SysAuthenticationMapper;
import com.todolist.backend.domain.mappers.SysUserMapper;
import com.todolist.backend.domain.model.SysAuthentication;
import com.todolist.backend.domain.model.SysUser;
import com.todolist.backend.domain.services.IdmService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/idm")
@RequiredArgsConstructor
public class IdmController {

    private final IdmService idmService;
    private final SysAuthenticationMapper sysAuthenticationMapper;


    @PostMapping("/signup")
    public ResponseEntity<SignUpResponse> signUp(@Valid @RequestBody SignUpRequest signUpRequest) {
        SysUser user = sysAuthenticationMapper.signupRequestToModel(signUpRequest);
        SignUpResponse response = sysAuthenticationMapper.modelToSignupResponse(idmService.signUp(user));
        return ResponseEntity.ok(response);
    }

    @PostMapping("/login")
    public ResponseEntity<SysAuthenticationResponse> login(@RequestBody SysAuthenticationRequest sysAuthenticationRequest) {
        SysAuthentication sysAuthentication = sysAuthenticationMapper.authenticationRequestToModel(sysAuthenticationRequest);
        SysAuthenticationResponse response = sysAuthenticationMapper.modelToAuthenticationResponse(
                idmService.login(sysAuthentication)
        );
        return ResponseEntity.ok(response);
    }
}
