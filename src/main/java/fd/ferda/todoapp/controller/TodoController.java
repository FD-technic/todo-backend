package fd.ferda.todoapp.controller;

import fd.ferda.todoapp.dto.TaskDTO;
import fd.ferda.todoapp.dto.TaskSaveDTO;
import fd.ferda.todoapp.filter.TaskFilter;
import fd.ferda.todoapp.service.TaskService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/todo")
public class TodoController {

    private final TaskService taskService;

    public TodoController(TaskService taskService) {
        this.taskService = taskService;
    }

    @PostMapping
    public TaskDTO addTask(@RequestBody TaskSaveDTO taskSaveDTO) {
        return taskService.addTask(taskSaveDTO);
    }

    @GetMapping
    public Page<TaskDTO> findTasks(@ModelAttribute TaskFilter filter) {

        Pageable pageable = PageRequest.of(filter.getPage(), filter.getPageSize());

        return taskService.findTasks(filter, pageable);
    }
}
