package com.katlego.taskflow.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.katlego.taskflow.dto.DashboardResponse;
import com.katlego.taskflow.dto.TaskRequest;
import com.katlego.taskflow.dto.TaskResponse;
import com.katlego.taskflow.entity.Priority;
import com.katlego.taskflow.entity.Status;
import com.katlego.taskflow.entity.Task;
import com.katlego.taskflow.entity.User;
import com.katlego.taskflow.exception.TaskNotFoundException;
import com.katlego.taskflow.repository.TaskRepository;
import com.katlego.taskflow.repository.UserRepository;

//This class contains business logic.
@Service
public class TaskServiceImpl implements TaskService {

    private final TaskRepository repository;
    private final UserRepository userRepository;

    public TaskServiceImpl(TaskRepository repository, UserRepository userRepository) {
        this.repository = repository;
        this.userRepository = userRepository;
    }

    private User getCurrentUser() {

        Authentication authentication =
                SecurityContextHolder.getContext().getAuthentication();

        String email = authentication.getName();

        return userRepository.findByEmail(email)
                .orElseThrow(() ->
                        new RuntimeException("User not found"));
    }

    @Override
    public TaskResponse createTask(TaskRequest request) {

        System.out.println("STEP 1");

        Task task = new Task();

        System.out.println("STEP 2");

        updateTaskFromRequest(task, request);

        System.out.println("STEP 3");

        task.setUser(getCurrentUser());

        System.out.println("STEP 4");

        Task savedTask = repository.save(task);

        System.out.println("STEP 5");

        return mapToResponse(savedTask);
    }

    @Override
    public Page<TaskResponse> getAllTasks(int page, int size, String sortBy) {

        Pageable pageable = PageRequest.of(
                page,
                size,
                Sort.by(sortBy).ascending());

        User user = getCurrentUser();

        return repository.findByUser(user, pageable)
                .map(this::mapToResponse);
    }

    @Override
    public TaskResponse getTaskById(Long id) {

        Task task = repository.findByIdAndUser(id, getCurrentUser())
                .orElseThrow(() -> new TaskNotFoundException(id));

        return mapToResponse(task);
    }

    @Override
    public TaskResponse updateTask(Long id, TaskRequest request) {

        Task task = repository.findByIdAndUser(id, getCurrentUser())
                .orElseThrow(() -> new TaskNotFoundException(id));

        updateTaskFromRequest(task, request);

        Task updatedTask = repository.save(task);

        return mapToResponse(updatedTask);
    }

    @Override
    public void deleteTask(Long id) {

        Task task = repository.findByIdAndUser(id, getCurrentUser())
                .orElseThrow(() -> new TaskNotFoundException(id));

        repository.delete(task);
    }

    @Override
    public List<TaskResponse> searchTasks(String title) {

        return repository.findByUserAndTitleContainingIgnoreCase(getCurrentUser(), title)
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    /**
     * Converts a Task entity into a TaskResponse DTO.
     */
    private TaskResponse mapToResponse(Task task) {

        TaskResponse response = new TaskResponse();

        response.setId(task.getId());
        response.setTitle(task.getTitle());
        response.setDescription(task.getDescription());
        response.setPriority(task.getPriority());
        response.setStatus(task.getStatus());
        response.setDueDate(task.getDueDate());
        response.setCreatedAt(task.getCreatedAt());
        response.setUpdatedAt(task.getUpdatedAt());

        return response;
    }

    private void updateTaskFromRequest(Task task, TaskRequest request) {

        task.setTitle(request.getTitle());
        task.setDescription(request.getDescription());
        task.setPriority(request.getPriority());
        task.setStatus(request.getStatus());
        task.setDueDate(request.getDueDate());
    }

    @Override
    public List<TaskResponse> getTasksByStatus(Status status) {

        return repository.findByUserAndStatus(getCurrentUser(), status)
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    @Override
    public List<TaskResponse> getTasksByPriority(Priority priority) {

        return repository.findByUserAndPriority(getCurrentUser(), priority)
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    @Override
    public DashboardResponse getDashboard() {

        DashboardResponse dashboard = new DashboardResponse();

        User user = getCurrentUser();

        dashboard.setTotalTasks(repository.countByUser(user));

        dashboard.setTodoTasks(
                repository.countByUserAndStatus(user, Status.TODO));

        dashboard.setInProgressTasks(
                repository.countByUserAndStatus(user, Status.IN_PROGRESS));

        dashboard.setCompletedTasks(
                repository.countByUserAndStatus(user, Status.COMPLETED));

        dashboard.setOverdueTasks(
                repository.countByUserAndDueDateBefore(user, LocalDate.now()));

        return dashboard;
    }

    @Override
    public List<TaskResponse> exportTasks() {

        return repository.findByUser(getCurrentUser())
                .stream()
                .map(this::mapToResponse)
                .toList();
    }
}