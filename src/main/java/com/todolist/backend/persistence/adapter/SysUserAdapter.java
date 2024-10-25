package com.todolist.backend.persistence.adapter;

import com.todolist.backend.application.exception.sysuser.UserNotFoundException;
import com.todolist.backend.domain.mappers.SysUserMapper;
import com.todolist.backend.domain.model.SysUser;
import com.todolist.backend.persistence.entity.SysUserEntity;
import com.todolist.backend.persistence.jpa.SysUserJpaRepository;
import com.todolist.backend.persistence.repository.SysUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class SysUserAdapter implements SysUserRepository {
    private final SysUserJpaRepository sysUserJpaRepository;
    private final SysUserMapper sysUserMapper;


    @Override
    public SysUser getUserById(String id) {
        return sysUserMapper.entityToModel(sysUserJpaRepository.findById(id).orElseThrow(
                () -> new UserNotFoundException("User with id " + id + " not found")
        ));
    }


    @Override
    public Page<SysUser> getAllUsers(int page, int size, String sortBy, String sortDirection) {
        Sort.Direction direction = Sort.Direction.fromString(sortDirection);
        Pageable pageable = PageRequest.of(page, size, Sort.by(direction, sortBy));

        Page<SysUserEntity> entities = sysUserJpaRepository.findAll(pageable);

        List<SysUser> users = entities
                .getContent()
                .stream()
                .map(sysUserMapper::entityToModel)
                .toList();

        return new PageImpl<>(users, pageable, entities.getTotalElements());
    }

    @Override
    public SysUser save(SysUser user) {
        SysUserEntity entity = sysUserMapper.modelToEntity(user);

        return sysUserMapper.entityToModel(sysUserJpaRepository.save(entity));
    }

    @Override
    public SysUser getUserByUsername(String username) {
        return sysUserMapper.entityToModel(sysUserJpaRepository.findByUsername(username).orElseThrow(
                () -> new UserNotFoundException("User with username " + username + " not found")
        ));
    }

}
