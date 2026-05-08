package fd.ferda.todoapp.controller;

import fd.ferda.todoapp.dto.TaskDTO;
import fd.ferda.todoapp.dto.TaskCreateDTO;
import fd.ferda.todoapp.dto.TaskEditDTO;
import fd.ferda.todoapp.filter.TaskFilter;
import fd.ferda.todoapp.service.TaskService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/todo")
public class TodoController {

    private final TaskService taskService;

    public TodoController(TaskService taskService) {
        this.taskService = taskService;
    }

    @PostMapping
    public TaskDTO addTask(@RequestBody @Valid TaskCreateDTO taskCreateDTO) {
        return taskService.addTask(taskCreateDTO);
    }

    @GetMapping
    public Page<TaskDTO> findTasks(@ModelAttribute TaskFilter filter) {

        Pageable pageable = PageRequest.of(filter.getPage(), filter.getPageSize());

        return taskService.findTasks(filter, pageable);
    }

    @PutMapping("/tasks/{id}/edit")
    public TaskDTO editTask(@PathVariable Long id, @RequestBody @Valid TaskEditDTO taskEditDTO) {
        System.out.println("edit");
        return taskService.editTask(id, taskEditDTO);
    }

    @PostMapping("/tasks/{id}/complete")
    public TaskDTO completeTask(@PathVariable Long id) {
        return taskService.completeTask(id);
    }

    @PostMapping("/tasks/{id}/delete")
    public void deleteTask(@PathVariable Long id) {
        taskService.deleteTask(id);
    }
}
