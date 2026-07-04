package com.katlego.taskflow.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.katlego.taskflow.entity.Priority;
import com.katlego.taskflow.entity.Status;
import com.katlego.taskflow.entity.Task;
import com.katlego.taskflow.entity.User;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
    
    List<Task> findByTitleContainingIgnoreCase(String title);

    List<Task> findByStatus(Status status);

    List<Task> findByPriority(Priority priority);

    List<Task> findByUserEmail(String email);

    long countByStatus(Status status);

    long countByDueDateBefore(LocalDate dueDate);

    Page<Task> findByUser(User user, Pageable pageable);

    Optional<Task> findByIdAndUser(Long id, User user);

    List<Task> findByUserAndTitleContainingIgnoreCase(User user, String title);

    List<Task> findByUserAndStatus(User user, Status status);

    List<Task> findByUserAndPriority(User user, Priority priority);

    long countByUser(User user);

    long countByUserAndStatus(User user, Status status);

    long countByUserAndDueDateBefore(User user, LocalDate date);

    List<Task> findByUser(User user);
}
