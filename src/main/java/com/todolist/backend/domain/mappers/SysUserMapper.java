package com.todolist.backend.domain.mappers;


import com.todolist.backend.application.dtos.idm.signup.SignUpRequest;
import com.todolist.backend.application.dtos.sysuser.userprofile.update.UpdateUserProfileRequest;
import com.todolist.backend.application.dtos.sysuser.userprofile.update.UpdateUserProfileResponse;
import com.todolist.backend.domain.model.SysUser;
import com.todolist.backend.persistence.entity.SysUserEntity;
import org.mapstruct.Mapper;

import javax.validation.Valid;

@Mapper(componentModel = "spring")
public interface SysUserMapper {
    SysUser entityToModel(SysUserEntity sysUserEntity);
    SysUserEntity modelToEntity(SysUser sysUser);

    SysUser updateUserProfileRequestToModel(UpdateUserProfileRequest updateUserProfileRequest);
    UpdateUserProfileResponse modelToUpdateUserProfileResponse(SysUser user);
}
