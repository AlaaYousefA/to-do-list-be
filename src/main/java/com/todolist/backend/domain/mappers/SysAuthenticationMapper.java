package com.todolist.backend.domain.mappers;

import com.todolist.backend.application.dtos.idm.login.SysAuthenticationRequest;
import com.todolist.backend.application.dtos.idm.login.SysAuthenticationResponse;
import com.todolist.backend.application.dtos.idm.signup.SignUpRequest;
import com.todolist.backend.application.dtos.idm.signup.SignUpResponse;
import com.todolist.backend.domain.model.SysAuthentication;
import com.todolist.backend.domain.model.SysUser;
import org.mapstruct.Mapper;

import javax.validation.Valid;

@Mapper(componentModel = "spring")
public interface SysAuthenticationMapper {
    SysUser signupRequestToModel(SignUpRequest signUpRequest);

    SignUpResponse modelToSignupResponse(SysUser sysUser);

    SysAuthentication authenticationRequestToModel(SysAuthenticationRequest sysAuthenticationRequest);

    SysAuthenticationResponse modelToAuthenticationResponse(SysAuthentication sysAuthentication);
}
