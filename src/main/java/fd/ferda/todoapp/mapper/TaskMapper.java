package fd.ferda.todoapp.mapper;

import fd.ferda.todoapp.dto.TaskDTO;
import fd.ferda.todoapp.dto.TaskCreateDTO;
import fd.ferda.todoapp.dto.TaskEditDTO;
import fd.ferda.todoapp.entity.TaskEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;


public class TaskMapper {

    public static TaskDTO toDTO(TaskEntity entity) {
        TaskDTO dto = new TaskDTO();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setDescription(entity.getDescription());
        dto.setEndDate(entity.getEndDate());
        dto.setFinishDate(entity.getFinishDate());

        return dto;
    }

    public static TaskEntity toEntity(TaskCreateDTO dto) {
        TaskEntity entity = new TaskEntity();
        entity.setName(dto.getName());
        entity.setDescription(dto.getDescription());
        entity.setEndDate(dto.getEndDate());

        return entity;
    }

    public static void updateEntity(TaskEntity entity, TaskEditDTO dto) {
        if (dto.getName() != null) {
            if (dto.getName().isBlank()) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
            }
            entity.setName(dto.getName());
        }

        if (dto.getDescription() != null) {
            entity.setDescription(dto.getDescription());
        }

        if (dto.getEndDate() != null) {
            entity.setEndDate(dto.getEndDate());
        }
    }
}
