package ma.taskmanagement.service;

import lombok.RequiredArgsConstructor;
import ma.taskmanagement.dto.TaskRequest;
import ma.taskmanagement.model.Task;
import ma.taskmanagement.model.User;
import ma.taskmanagement.repository.TaskRepository;
import ma.taskmanagement.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskService {

    private final TaskRepository taskRepository;
    private final UserRepository userRepository;

    public List<Task> getAllTasks(String username){
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));
        return taskRepository.findByUser(user);
    }

    public Task createTask(TaskRequest taskRequest, String username){
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));
        Task task = Task.builder()
                .title(taskRequest.getTitle())
                .description(taskRequest.getDescription())
                .status(taskRequest.getStatus())
                .user(user)
                .build();

        return taskRepository.save(task);
    }

    public Task updateTask(Long id, TaskRequest taskRequest){
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Task not found"));
        if(taskRequest.getTitle() != null){
            task.setTitle(taskRequest.getTitle());
        }
        if(taskRequest.getDescription() != null){
            task.setDescription(taskRequest.getDescription());
        }
        if(taskRequest.getStatus() != null){
            task.setStatus(taskRequest.getStatus());
        }

        return taskRepository.save(task);
    }

    public void deleteTask(Long id){
        taskRepository.deleteById(id);
    }
}
