package ma.taskmanagement.repository;

import ma.taskmanagement.model.Task;
import ma.taskmanagement.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {
    List<Task> findByUser(User user);
    void deleteByUser(User user);

}
