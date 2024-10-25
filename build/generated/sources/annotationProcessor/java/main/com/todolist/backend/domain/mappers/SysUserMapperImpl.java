package com.todolist.backend.domain.mappers;

import com.todolist.backend.application.dtos.sysuser.userprofile.update.UpdateUserProfileRequest;
import com.todolist.backend.application.dtos.sysuser.userprofile.update.UpdateUserProfileResponse;
import com.todolist.backend.domain.model.SysUser;
import com.todolist.backend.persistence.entity.SysUserEntity;
import java.util.Arrays;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-09-07T12:16:41+0300",
    comments = "version: 1.5.3.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-8.8.jar, environment: Java 22.0.2 (Oracle Corporation)"
)
@Component
public class SysUserMapperImpl implements SysUserMapper {

    @Override
    public SysUser entityToModel(SysUserEntity sysUserEntity) {
        if ( sysUserEntity == null ) {
            return null;
        }

        SysUser.SysUserBuilder sysUser = SysUser.builder();

        sysUser.id( sysUserEntity.getId() );
        sysUser.username( sysUserEntity.getUsername() );
        sysUser.password( sysUserEntity.getPassword() );
        sysUser.email( sysUserEntity.getEmail() );
        sysUser.numberOfTasks( sysUserEntity.getNumberOfTasks() );
        byte[] profileImage = sysUserEntity.getProfileImage();
        if ( profileImage != null ) {
            sysUser.profileImage( Arrays.copyOf( profileImage, profileImage.length ) );
        }
        sysUser.bio( sysUserEntity.getBio() );

        return sysUser.build();
    }

    @Override
    public SysUserEntity modelToEntity(SysUser sysUser) {
        if ( sysUser == null ) {
            return null;
        }

        SysUserEntity sysUserEntity = new SysUserEntity();

        sysUserEntity.setId( sysUser.getId() );
        sysUserEntity.setUsername( sysUser.getUsername() );
        sysUserEntity.setPassword( sysUser.getPassword() );
        sysUserEntity.setEmail( sysUser.getEmail() );
        sysUserEntity.setNumberOfTasks( sysUser.getNumberOfTasks() );
        byte[] profileImage = sysUser.getProfileImage();
        if ( profileImage != null ) {
            sysUserEntity.setProfileImage( Arrays.copyOf( profileImage, profileImage.length ) );
        }
        sysUserEntity.setBio( sysUser.getBio() );

        return sysUserEntity;
    }

    @Override
    public SysUser updateUserProfileRequestToModel(UpdateUserProfileRequest updateUserProfileRequest) {
        if ( updateUserProfileRequest == null ) {
            return null;
        }

        SysUser.SysUserBuilder sysUser = SysUser.builder();

        sysUser.bio( updateUserProfileRequest.getBio() );

        return sysUser.build();
    }

    @Override
    public UpdateUserProfileResponse modelToUpdateUserProfileResponse(SysUser user) {
        if ( user == null ) {
            return null;
        }

        UpdateUserProfileResponse.UpdateUserProfileResponseBuilder updateUserProfileResponse = UpdateUserProfileResponse.builder();

        updateUserProfileResponse.id( user.getId() );
        updateUserProfileResponse.bio( user.getBio() );

        return updateUserProfileResponse.build();
    }
}
