package fd.ferda.todoapp.mapper;

import fd.ferda.todoapp.dto.TaskDTO;
import fd.ferda.todoapp.dto.TaskSaveDTO;
import fd.ferda.todoapp.entity.TaskEntity;
import org.springframework.stereotype.Component;


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

    public static TaskEntity toEntity(TaskSaveDTO dto) {
        TaskEntity entity = new TaskEntity();
        entity.setName(dto.getName());
        entity.setDescription(dto.getDescription());
        entity.setEndDate(dto.getEndDate());

        return entity;
    }

    public static void updateEntity(TaskEntity entity, TaskSaveDTO dto) {
        if (dto.getName() != null) {
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
