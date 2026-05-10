package fd.ferda.todoapp.service;

import fd.ferda.todoapp.dto.TaskDTO;
import fd.ferda.todoapp.dto.TaskCreateDTO;
import fd.ferda.todoapp.dto.TaskEditDTO;
import fd.ferda.todoapp.entity.TaskEntity;
import fd.ferda.todoapp.filter.TaskFilter;
import fd.ferda.todoapp.mapper.TaskMapper;
import fd.ferda.todoapp.repository.TaskRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;

import static fd.ferda.todoapp.specification.TaskSpecification.build;

@Service
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;

    public TaskServiceImpl(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Override
    public TaskDTO addTask(TaskCreateDTO task) {

        TaskEntity entity = TaskMapper.toEntity(task);

        entity = taskRepository.save(entity);
        return TaskMapper.toDTO(entity);
    }

    @Override
    @Transactional
    public TaskDTO editTask(Long id, TaskEditDTO task) {
        System.out.println("EDIT!");
        TaskEntity entity = getTaskOrThrow(id);

        TaskMapper.updateEntity(entity, task);

        TaskEntity saved = taskRepository.save(entity);

        return TaskMapper.toDTO(saved);
    }

    @Override
    public Page<TaskDTO> findTasks(TaskFilter filter, Pageable pageable) {
        Specification<TaskEntity> spec = build(filter, LocalDate.now());
        System.out.println("Filter: " + spec);

        return taskRepository.findAll(spec, pageable)
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
}
