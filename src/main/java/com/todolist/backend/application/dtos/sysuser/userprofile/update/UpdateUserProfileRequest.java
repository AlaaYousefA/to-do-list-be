package com.todolist.backend.application.dtos.sysuser.userprofile.update;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class UpdateUserProfileRequest {
    private Byte[] image;
    private String bio;
}
