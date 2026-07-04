package com.katlego.taskflow.dto;

import java.time.LocalDate;

import com.katlego.taskflow.entity.Priority;
import com.katlego.taskflow.entity.Status;

import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

//This class represents the data a client sends when creating or updating a task.
public class TaskRequest {

    @NotBlank(message = "Title is required.")
    @Size(min = 3, max = 100, message = "Title must be between 3 and 100 characters.")
    private String title;

    @NotBlank(message = "Description is required.")
    @Size(max = 500, message = "Description cannot exceed 500 characters.")
    private String description;

    @NotNull(message = "Priority is required.")
    private Priority priority;
    
    @NotNull(message = "Status is required.")
    private Status status;
    
    @NotNull(message = "Due date is required.")
    @FutureOrPresent(message = "Due date cannot be in the past.")
    private LocalDate dueDate;

    public TaskRequest(){
    }
    
    //Getters and Setters
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Priority getPriority() {
        return priority;
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }
}
