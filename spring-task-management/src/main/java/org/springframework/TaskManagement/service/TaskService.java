package org.springframework.TaskManagement.service;

import jakarta.validation.Valid;
import org.springframework.TaskManagement.model.Task;
import org.springframework.TaskManagement.repository.TaskRepository;

import org.springframework.TaskManagement.util.CommonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    public List<Task> findAll() {
        return taskRepository.findAll();
    }

    public Task findById(UUID id) {
        return taskRepository.findById(id).orElse(null);
    }

    public boolean existsById(UUID id) {
        return taskRepository.existsById(id);
    }

    public List<Task> findByTitle(String title) {
        return taskRepository.findByTitle(title);
    }

    public Task save(@Valid Task task) {
        return taskRepository.save(task);
    }

    public Task update(Task task) {
        boolean isTaskExist = existsById(task.getId());
        if (isTaskExist) { return taskRepository.save(task); }
        return null;
    }

    public void deleteById(UUID id) {
        taskRepository.deleteById(id);
    }

}
