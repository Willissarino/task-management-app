package org.springframework.TaskManagement.repository;

import org.springframework.TaskManagement.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;
import java.util.UUID;

@CrossOrigin
public interface TaskRepository extends JpaRepository<Task, UUID> {

    List<Task> findByTitle(String title);
}
