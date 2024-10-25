package com.todolist.backend.domain.services;

import com.todolist.backend.domain.model.SysUser;
import com.todolist.backend.domain.model.UserProfile;
import com.todolist.backend.domain.services.security.jwt.JwtService;
import com.todolist.backend.persistence.repository.SysUserRepository;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SysUsersService {

    private final SysUserRepository sysUserRepository;
    private final JwtService jwtService;


    public SysUser getUserById(String id, HttpServletRequest header) {
        String token = jwtService.extractToken(header);
        String username = jwtService.extractUsername(token);

       SysUser user = sysUserRepository.getUserById(id);

        if(user.getUsername().equals(username)) {
            return user;
        }
        throw new RuntimeException("you cant access private endpoint");

    }

    public SysUser updateUserProfileInfo(String id, SysUser model) {
        SysUser user = sysUserRepository.getUserById(id);
        user.setBio(model.getBio());
        user.setProfileImage(model.getProfileImage());


        return sysUserRepository.save(user);
    }


    public Page<SysUser> getAllUsers(int page, int size, String sortBy, String sortDirection) {
        return sysUserRepository.getAllUsers(page, size, sortBy, sortDirection);
    }

    public UserProfile viewUserProfile(String userId) {
        SysUser sysUser = sysUserRepository.getUserById(userId);

        UserProfile userProfile = new UserProfile();

        userProfile.setUsername(sysUser.getUsername());
        userProfile.setProfileImage(sysUser.getProfileImage());
        userProfile.setBio(sysUser.getBio());

        return userProfile;
    }
}
