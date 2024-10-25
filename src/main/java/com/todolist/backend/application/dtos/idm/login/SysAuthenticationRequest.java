package com.todolist.backend.application.dtos.idm.login;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SysAuthenticationRequest {
    private String username;
    private String password;
}
