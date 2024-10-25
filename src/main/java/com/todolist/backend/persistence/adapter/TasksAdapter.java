package com.todolist.backend.persistence.adapter;

import com.todolist.backend.application.dtos.task.TaskRequest;
import com.todolist.backend.application.exception.sysuser.UserNotFoundException;
import com.todolist.backend.application.exception.task.TaskNotFoundException;
import com.todolist.backend.domain.mappers.TaskMapper;
import com.todolist.backend.domain.model.SysUser;
import com.todolist.backend.domain.model.Task;
import com.todolist.backend.persistence.entity.SysUserEntity;
import com.todolist.backend.persistence.entity.TaskEntity;
import com.todolist.backend.persistence.jpa.TasksJpaRepository;
import com.todolist.backend.persistence.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class TasksAdapter implements TaskRepository {

    private final TasksJpaRepository tasksJpaRepository;
    private final TaskMapper taskMapper;

    @Override
    public Task saveTask(Task task) {
        TaskEntity entity = taskMapper.taskToTaskEntity(task);

        return taskMapper.taskEntityToTask(tasksJpaRepository.save(entity));
    }

    @Override
    public Task getTaskById(String taskId) {
        return taskMapper.taskEntityToTask(
                tasksJpaRepository.findById(taskId).orElseThrow(
                        () -> new TaskNotFoundException("no task found with id: " + taskId)
                )
        );
    }

    @Override
    public Page<Task> getAllTasksByUserId(String userId, int page, int size, String sortBy, String sortDirection) {
        Sort.Direction direction = Sort.Direction.fromString(sortDirection);
        Pageable pageable = PageRequest.of(page, size, Sort.by(direction, sortBy));

        Page<TaskEntity> entities = tasksJpaRepository.findAll(pageable);

        List<Task> tasks = entities
                .getContent()
                .stream()
                .map(taskMapper::entityToModel)
                .toList();

        return new PageImpl<>(tasks, pageable, entities.getTotalElements());
    }

    @Override
    public String deleteTaskById(String taskId) {
        String id = taskId;
        if (tasksJpaRepository.existsById(taskId)) {
            tasksJpaRepository.deleteById(id);
        } else {
            throw new TaskNotFoundException("no task found with id: " + taskId);
        }
        return id;
    }

    @Override
    public String deleteAllTasks(String userId) {

        if (!tasksJpaRepository.existsByUserId(userId)) {
            // If no tasks exist, return a message
            return "No tasks found for user " + userId + ".";
        }
        try {
            tasksJpaRepository.deleteByUserId(userId);
            return "All tasks for user " + userId + " have been deleted successfully.";
        } catch (EmptyResultDataAccessException e) {
            // Handle the case where no tasks exist for the given userId
            return "No tasks found for user " + userId + ".";
        } catch (DataAccessException e) {
            // Handle other database-related exceptions
            return "Failed to delete tasks for user " + userId + ": " + e.getMessage();
        } catch (Exception e) {
            // Handle any other exceptions that may occur
            return "An unexpected error occurred while deleting tasks for user " + userId + ": " + e.getMessage();
        }
    }
}
