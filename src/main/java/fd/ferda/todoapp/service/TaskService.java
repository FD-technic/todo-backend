package fd.ferda.todoapp.service;

import fd.ferda.todoapp.dto.TaskDTO;
import fd.ferda.todoapp.dto.TaskSaveDTO;
import fd.ferda.todoapp.filter.TaskFilter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


import java.util.List;

public interface TaskService {

    TaskDTO addTask(TaskSaveDTO task);

    TaskDTO editTask(Long id, TaskSaveDTO task);

    Page<TaskDTO> findTasks(TaskFilter filter, Pageable pageable);

    void completeTask(Long id);

    void deleteTask(Long id);

}
