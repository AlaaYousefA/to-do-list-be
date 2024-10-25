package com.todolist.backend.persistence.repository;

import com.todolist.backend.domain.model.SysUser;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Repository;

@Repository
public interface SysUserRepository {

    SysUser getUserById(String id);

    Page<SysUser> getAllUsers(int page, int size, String sortBy, String sortDirection);

    SysUser save(SysUser user);

    SysUser getUserByUsername(String username);
}
