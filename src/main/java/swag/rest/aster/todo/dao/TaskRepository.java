package swag.rest.aster.todo.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import swag.rest.aster.todo.entity.Task;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {}
