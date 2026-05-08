package fd.ferda.todoapp.service;

import fd.ferda.todoapp.dto.TaskDTO;
import fd.ferda.todoapp.dto.TaskSaveDTO;
import fd.ferda.todoapp.entity.TaskEntity;
import fd.ferda.todoapp.filter.TaskFilter;
import fd.ferda.todoapp.mapper.TaskMapper;
import fd.ferda.todoapp.repository.TaskRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.List;

@Service
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;

    public TaskServiceImpl(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Override
    public TaskDTO addTask(TaskSaveDTO task) {

        validateTaskName(task);

        if (task.getEndDate() == null || task.getEndDate().isBefore(LocalDate.now())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Task end date cannot be before today");
        }
        TaskEntity entity = TaskMapper.toEntity(task);

        entity = taskRepository.save(entity);
        return TaskMapper.toDTO(entity);
    }

    @Override
    @Transactional
    public TaskDTO editTask(Long id, TaskSaveDTO task) {
        TaskEntity entity = getTaskOrThrow(id);

        validateTaskName(task);

        TaskMapper.updateEntity(entity, task);

        TaskEntity saved = taskRepository.save(entity);

        return TaskMapper.toDTO(saved);
    }

    @Override
    public Page<TaskDTO> findTasks(TaskFilter filter, Pageable pageable) {
        return taskRepository.findAll(pageable)
                .map(TaskMapper::toDTO);
    }

    @Override
    public TaskDTO completeTask(Long id) {
        System.out.println("Complete ID: " + id);

        TaskEntity entity = getTaskOrThrow(id);

        if (entity.getFinishDate() == null) {
            entity.setFinishDate(LocalDate.now());
        }

        return TaskMapper.toDTO(taskRepository.save(entity));
    }

    @Override
    public void deleteTask(Long id) {
        TaskEntity entity = getTaskOrThrow(id);
        taskRepository.delete(entity);
    }

    // === DRY ===
    private TaskEntity getTaskOrThrow(Long id) {

        return taskRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Hledaný záznam nenalezen"));
    }

    private void validateTaskName(TaskSaveDTO task) {
        if (task == null || task.getName() == null || task.getName().isBlank()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Task name is required");
        }
    }

}
