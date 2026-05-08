package fd.ferda.todoapp.dto;

import java.time.LocalDate;

public class TaskSaveDTO {

    private String name;

    private String description;

    private LocalDate endDate;

    public TaskSaveDTO() {}

    public TaskSaveDTO(String name, String description, LocalDate endDate) {
        this.name = name;
        this.description = description;
        this.endDate = endDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }
}
