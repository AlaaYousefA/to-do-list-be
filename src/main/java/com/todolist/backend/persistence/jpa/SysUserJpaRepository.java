package com.todolist.backend.persistence.jpa;

import com.todolist.backend.persistence.entity.SysUserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SysUserJpaRepository extends JpaRepository<SysUserEntity, String > {
    Optional<SysUserEntity> findByUsername(String username);
}
