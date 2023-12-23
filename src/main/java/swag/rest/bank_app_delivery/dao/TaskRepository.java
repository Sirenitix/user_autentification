package swag.rest.bank_app_delivery.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import swag.rest.bank_app_delivery.entity.Task;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {}
