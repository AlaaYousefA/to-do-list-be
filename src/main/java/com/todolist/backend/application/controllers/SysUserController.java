package com.todolist.backend.application.controllers;

import  com.todolist.backend.application.dtos.sysuser.userprofile.update.UpdateUserProfileRequest;
import com.todolist.backend.application.dtos.sysuser.userprofile.update.UpdateUserProfileResponse;
import com.todolist.backend.domain.mappers.SysUserMapper;
import com.todolist.backend.domain.model.SysUser;
import com.todolist.backend.domain.model.UserProfile;
import com.todolist.backend.domain.services.SysUsersService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/users")
@AllArgsConstructor
public class SysUserController {

    private final SysUsersService sysUsersService;
    private final SysUserMapper sysUserMapper;

    @GetMapping
    public ResponseEntity<Page<SysUser>> getAllUsers(
            @RequestParam(defaultValue = "0", name = "page") int page,
            @RequestParam(defaultValue = "10", name = "size") int size,
            @RequestParam(defaultValue = "username", name = "sortBy") String sortBy,
            @RequestParam(defaultValue = "asc", name = "sortDirection") String sortDirection
    ) {
        return ResponseEntity.ok(sysUsersService.getAllUsers(page, size, sortBy, sortDirection));

    }

    @GetMapping("/{id}")
    public ResponseEntity<SysUser> getUserById(@PathVariable("id") String id, HttpServletRequest header) {
        return ResponseEntity.ok(sysUsersService.getUserById(id, header));
    }


    @PutMapping("/{id}")
    public ResponseEntity<UpdateUserProfileResponse> updateUserProfileInfo(@PathVariable("id") String id, @RequestBody UpdateUserProfileRequest userProfileRequest) {
        SysUser model = sysUserMapper.updateUserProfileRequestToModel(userProfileRequest);
        return ResponseEntity.ok(
                sysUserMapper.modelToUpdateUserProfileResponse(sysUsersService.updateUserProfileInfo(id, model))
        );
    }

    @GetMapping("/profile/{id}")
    public ResponseEntity<UserProfile> viewUserProfile(@PathVariable("id") String userId) {
        return ResponseEntity.ok(sysUsersService.viewUserProfile(userId));
    }

}
