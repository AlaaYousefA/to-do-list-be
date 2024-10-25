package com.todolist.backend.persistence.repository;

import com.todolist.backend.application.dtos.task.TaskResponse;
import com.todolist.backend.domain.model.Task;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepository {
    Task saveTask(Task task);

    Task getTaskById(String taskId);

    Page<Task> getAllTasksByUserId(String userId, int page, int size, String sortBy, String sortDirection);

    String deleteTaskById(String taskId);

    String deleteAllTasks(String userId);
}
