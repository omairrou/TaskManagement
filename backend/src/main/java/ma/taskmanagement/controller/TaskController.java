package ma.taskmanagement.controller;

import lombok.RequiredArgsConstructor;
import ma.taskmanagement.dto.TaskRequest;
import ma.taskmanagement.model.Task;
import ma.taskmanagement.service.TaskService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tasks")
@RequiredArgsConstructor
public class TaskController {

    private final TaskService taskService;

    @GetMapping
    public ResponseEntity<List<Task>> getAllTasks(Authentication authentication){
        return ResponseEntity.ok(taskService.getAllTasks(authentication.getName()));
    }

    @PostMapping
    public ResponseEntity<Task> createTask(@RequestBody TaskRequest task, Authentication authentication){
        return ResponseEntity.ok(taskService.createTask(task,authentication.getName()));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Task> updateTask(@PathVariable Long id, @RequestBody TaskRequest taskRequest){
        return ResponseEntity.ok(taskService.updateTask(id, taskRequest));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable Long id){
        taskService.deleteTask(id);
        return ResponseEntity.noContent().build();
    }
}
