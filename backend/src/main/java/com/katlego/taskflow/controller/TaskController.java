package com.katlego.taskflow.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.katlego.taskflow.dto.DashboardResponse;
import com.katlego.taskflow.dto.TaskRequest;
import com.katlego.taskflow.dto.TaskResponse;
import com.katlego.taskflow.entity.Priority;
import com.katlego.taskflow.entity.Status;
import com.katlego.taskflow.service.TaskService;
import com.katlego.taskflow.util.CsvExporter;
import com.katlego.taskflow.util.PdfExporter;

import org.springframework.security.core.Authentication;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;

@RestController                 //This class handles HTTP requests and returns JSON.
@RequestMapping("/api/tasks")   //Every endpoint in this controller begins with
public class TaskController {

    private final TaskService service;

    public TaskController(TaskService service){
        this.service = service;
    }

    //Maps HTTP POST requests.
    @PostMapping
    public ResponseEntity<TaskResponse> createTask(
            @Valid @RequestBody TaskRequest request) {

        TaskResponse response = service.createTask(request);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TaskResponse> getTaskById(@PathVariable Long id){

        return ResponseEntity.ok(service.getTaskById(id));

    }

    @GetMapping("/test")
    public String test() {
        return "TaskFlow API is working!";
    }

    @GetMapping
    public ResponseEntity<Page<TaskResponse>> getAllTasks(

            @RequestParam(defaultValue = "0") int page,

            @RequestParam(defaultValue = "5") int size,

            @RequestParam(defaultValue = "id") String sort) {

        return ResponseEntity.ok(
                service.getAllTasks(page, size, sort));
    }

    @GetMapping("/whoami")
    public String whoAmI() {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        return auth.getName() + " | " + auth.getAuthorities();
    }

    @PutMapping("/{id}")
    public ResponseEntity<TaskResponse> updateTask(
            @PathVariable Long id,
            @Valid @RequestBody TaskRequest request) {

        return ResponseEntity.ok(service.updateTask(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable Long id){
        service.deleteTask(id);

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/search")
    public ResponseEntity<List<TaskResponse>> searchTasks(
            @RequestParam String title) {

        return ResponseEntity.ok(service.searchTasks(title));
    }

    @GetMapping("/status/{status}")
    public ResponseEntity<List<TaskResponse>> getTasksByStatus(
            @PathVariable Status status) {

        return ResponseEntity.ok(
                service.getTasksByStatus(status));
    }

    @GetMapping("/priority/{priority}")
    public ResponseEntity<List<TaskResponse>> getTasksByPriority(
            @PathVariable Priority priority) {

        return ResponseEntity.ok(
                service.getTasksByPriority(priority));
    }

    @GetMapping("/dashboard")
    public ResponseEntity<DashboardResponse> getDashboard() {

        return ResponseEntity.ok(service.getDashboard());
    }

    @GetMapping("/export/csv")
    public void exportCSV(HttpServletResponse response)
            throws IOException {

        response.setContentType("text/csv");

        response.setHeader(
                "Content-Disposition",
                "attachment; filename=tasks.csv");

        PrintWriter writer = response.getWriter();

        CsvExporter.writeTasks(
                writer,
                service.exportTasks());
    }

    @GetMapping("/export/pdf")
    public void exportPDF(HttpServletResponse response)
            throws IOException {

        response.setContentType("application/pdf");

        response.setHeader(
                "Content-Disposition",
                "attachment; filename=TaskFlow_Report.pdf");

        List<TaskResponse> tasks = service.exportTasks();

        PdfExporter exporter = new PdfExporter(tasks);

        exporter.export(response);
    }

    @PostMapping("/test")
    public String postTest() {
        return "POST works";
    }
}
