package com.todolist.backend.persistence.jpa;

import com.todolist.backend.domain.model.Task;
import com.todolist.backend.persistence.entity.TaskEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TasksJpaRepository extends JpaRepository<TaskEntity, String> {
    // Custom method to delete all tasks by userId
    void deleteByUserId(String userId);

    boolean existsByUserId(String userId);
}
