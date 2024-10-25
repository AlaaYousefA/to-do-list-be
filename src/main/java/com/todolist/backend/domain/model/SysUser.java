package com.todolist.backend.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class SysUser {
    private String id;

    private String username;

    private String password;

    private String email;

    private Long numberOfTasks;

    private byte[] profileImage;

    private String bio;
}
