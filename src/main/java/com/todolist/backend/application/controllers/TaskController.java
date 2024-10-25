package com.todolist.backend.application.controllers;

import com.todolist.backend.application.dtos.task.TaskRequest;
import com.todolist.backend.application.dtos.task.TaskResponse;
import com.todolist.backend.domain.mappers.TaskMapper;
import com.todolist.backend.domain.model.Task;
import com.todolist.backend.domain.services.TasksService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/tasks")
@AllArgsConstructor
public class TaskController {
    private final TasksService tasksService;
    private final TaskMapper taskMapper;


    @PostMapping
    public ResponseEntity<TaskResponse> createTask(@RequestBody TaskRequest taskRequest) {
        Task task = taskMapper.taskRequestToTask(taskRequest);

        return ResponseEntity.ok(taskMapper.taskToTaskResponse(tasksService.createTask(task)));
    }

    @PutMapping("/{idTask}")
    public ResponseEntity<TaskResponse> updateTask(@PathVariable String idTask, @RequestBody TaskRequest taskRequest) {
        Task task = taskMapper.taskRequestToTask(taskRequest);

        return ResponseEntity.ok(
                taskMapper.taskToTaskResponse(tasksService.updateTask(idTask, task))
        );
    }

    @GetMapping("/{taskId}")
    public ResponseEntity<Task> getTaskById(@PathVariable String taskId) {
        return ResponseEntity.ok(tasksService.getTaskById(taskId));
    }

    @GetMapping("/{userId}")
    public ResponseEntity<Page<Task>> getAllTasksByUserId(
            @PathVariable String userId,
            @RequestParam(defaultValue = "0", name = "page") int page,
            @RequestParam(defaultValue = "10", name = "size") int size,
            @RequestParam(defaultValue = "createdAt", name = "sortBy") String sortBy,
            @RequestParam(defaultValue = "asc", name = "sortDirection") String sortDirection
    ) {
        return ResponseEntity.ok(tasksService.getAllTasksByUserId(userId, page, size, sortBy, sortDirection));
    }

    @DeleteMapping("/{taskId}")
    public ResponseEntity<String> deleteTaskById(@PathVariable String taskId) {
        return ResponseEntity.ok(tasksService.deleteTaskById(taskId));
    }

    @DeleteMapping("{userId}/")
    public ResponseEntity<String> deleteAllTasks(@PathVariable String userId) {
        return ResponseEntity.ok(tasksService.deleteAllTasks(userId));
    }

}
