package com.todolist.backend.domain.services;

import com.todolist.backend.application.dtos.task.TaskResponse;
import com.todolist.backend.domain.mappers.TaskMapper;
import com.todolist.backend.domain.model.Task;
import com.todolist.backend.persistence.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class TasksService {
    private final TaskRepository taskRepository;
    private final TaskMapper taskMapper;

    public Task createTask(Task task) {
        task.setCreatedAt(LocalDateTime.now());
        task.setUpdatedAt(LocalDateTime.now());

        return taskRepository.saveTask(task);
    }

    public Task getTaskById(String taskId) {
        return taskRepository.getTaskById(taskId);
    }

    public Page<Task> getAllTasksByUserId(String userId, int page, int size, String sortBy, String sortDirection) {
        return taskRepository.getAllTasksByUserId(userId, page, size, sortBy, sortDirection);

    }

    public Task updateTask(String idTask, Task task) {
        Task oldTask = taskRepository.getTaskById(idTask);

        task.setUpdatedAt(LocalDateTime.now());
        task.setCreatedAt(oldTask.getCreatedAt());
        task.setTaskId(idTask);
        return taskRepository.saveTask(task);
    }

    public String deleteTaskById(String taskId) {
        return taskRepository.deleteTaskById(taskId);
    }

    public String deleteAllTasks(String userId) {
        return taskRepository.deleteAllTasks(userId);
    }
}
