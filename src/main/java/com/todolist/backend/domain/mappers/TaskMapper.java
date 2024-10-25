package com.todolist.backend.domain.mappers;

import com.todolist.backend.application.dtos.task.TaskRequest;
import com.todolist.backend.application.dtos.task.TaskResponse;
import com.todolist.backend.domain.model.Task;
import com.todolist.backend.persistence.entity.TaskEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface TaskMapper {
    Task taskRequestToTask(TaskRequest taskRequest);
    TaskResponse taskToTaskResponse(Task task);

    @Mapping(source = "userId", target = "user.id")
    TaskEntity taskToTaskEntity(Task task);

    @Mapping(source = "user.id", target = "userId")
    Task taskEntityToTask(TaskEntity taskEntity);

    Task entityToModel(TaskEntity taskEntity);
}
