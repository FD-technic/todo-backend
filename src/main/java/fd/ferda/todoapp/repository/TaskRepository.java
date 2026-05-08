package fd.ferda.todoapp.repository;

import fd.ferda.todoapp.entity.TaskEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<TaskEntity, Long> {
}
