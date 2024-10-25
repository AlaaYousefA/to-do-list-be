package com.todolist.backend.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Task {
    private String taskId;

    private String userId;

    private String taskName;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}
