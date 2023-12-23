package swag.rest.aster.todo.service;

import java.util.List;
import java.util.Optional;
import javax.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import swag.rest.aster.todo.dao.TaskRepository;
import swag.rest.aster.todo.entity.Task;

@Service
@RequiredArgsConstructor
public class TaskService {

    private final TaskRepository taskRepository;

    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    public Task createTask(Task task) {
        return taskRepository.save(task);
    }

    public Task updateTask(Long id, Task task) {
        Optional<Task> taskToUpdate = taskRepository.findById(id);
        taskToUpdate.ifPresent(
            taskFromDb -> {
                taskFromDb.setDescription(task.getDescription());
                taskFromDb.setName(task.getDescription());
            }
        );
        return taskRepository.save(taskToUpdate.orElseThrow(EntityNotFoundException::new));
    }

    public void deleteTask(Long id) {
        taskRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        taskRepository.deleteById(id);
    }
}
