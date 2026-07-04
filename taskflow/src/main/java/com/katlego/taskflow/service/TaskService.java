package com.katlego.taskflow.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.katlego.taskflow.dto.DashboardResponse;
import com.katlego.taskflow.dto.TaskRequest;
import com.katlego.taskflow.dto.TaskResponse;
import com.katlego.taskflow.entity.Priority;
import com.katlego.taskflow.entity.Status;

public interface TaskService {

    TaskResponse createTask(TaskRequest request);
    
    Page<TaskResponse> getAllTasks(int page, int size, String sortBy);

    TaskResponse getTaskById(Long id);

    TaskResponse updateTask(Long id, TaskRequest request);

    void deleteTask(Long id);

    List<TaskResponse> searchTasks(String title);

    List<TaskResponse> getTasksByStatus(Status status);

    List<TaskResponse> getTasksByPriority(Priority priority);

    DashboardResponse getDashboard();

    List<TaskResponse> exportTasks();
}
