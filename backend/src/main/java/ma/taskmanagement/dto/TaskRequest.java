package ma.taskmanagement.dto;

import lombok.Data;
import ma.taskmanagement.model.TaskStatus;

@Data
public class TaskRequest {
    private String title;
    private String description;
    private TaskStatus status;
}
