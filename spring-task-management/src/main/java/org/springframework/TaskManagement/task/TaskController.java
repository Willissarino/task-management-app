package org.springframework.TaskManagement.task;

import org.springframework.TaskManagement.model.Task;
import org.springframework.TaskManagement.service.TaskService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;


@CrossOrigin
@RestController
@RequestMapping("/tasks")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @GetMapping
    public ResponseEntity <List<Task>> getAllTasks() {
        List<Task> tasks = taskService.findAll();
        return ResponseEntity.ok(tasks);
    }

    @GetMapping("/findById/{id}")
    public ResponseEntity <Task> findById(@PathVariable UUID id) {
        Task foundTask = taskService.findById(id);
        return ResponseEntity.ok(foundTask);
    }

    @GetMapping("/findByTitle/{title}")
    public ResponseEntity <List<Task>> findByTitle(@PathVariable String title) {
        List<Task> tasks = taskService.findByTitle(title);
        return ResponseEntity.ok(tasks);
    }

    @PostMapping("/createTask")
    public ResponseEntity<Task> createTask(@RequestBody Task task) {
        Task addedTask = taskService.save(task);
        return new ResponseEntity<>(addedTask, HttpStatus.CREATED);
    }

    @PutMapping("/updateTask")
    public ResponseEntity<Object> updateTask(@RequestBody Task task) {
        Task updatedTask = taskService.update(task);
        Map<String, Object> response = new HashMap<>();
        if (updatedTask == null ) {
            response.put("message", "No ID found");
            response.put("status", HttpStatus.BAD_REQUEST);
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(updatedTask, HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/deleteById")
    public ResponseEntity<Map<String, Object>> deleteById(@RequestParam UUID taskId) {
        boolean isExist = taskService.existsById(taskId);
        Map<String, Object> response = new HashMap<>();

        if (isExist) {
            taskService.deleteById(taskId);
            response.put("message", "ID: " + taskId + " successfully deleted.");
            response.put("status", HttpStatus.ACCEPTED.value());
            return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
        }

        response.put("message", "No ID found");
        response.put("status", HttpStatus.BAD_REQUEST.value());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

}
