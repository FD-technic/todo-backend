package fd.ferda.todoapp.service;

import fd.ferda.todoapp.dto.TaskDTO;
import fd.ferda.todoapp.dto.TaskCreateDTO;
import fd.ferda.todoapp.dto.TaskEditDTO;
import fd.ferda.todoapp.filter.TaskFilter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface TaskService {

    TaskDTO addTask(TaskCreateDTO task);

    TaskDTO editTask(Long id, TaskEditDTO task);

    Page<TaskDTO> findTasks(TaskFilter filter, Pageable pageable);

    TaskDTO completeTask(Long id);

    void deleteTask(Long id);

}
