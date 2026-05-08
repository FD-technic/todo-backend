package fd.ferda.todoapp.dto;


import java.time.LocalDate;

public class TaskDTO {

    private Long id;

    private String name;

    private String description;

    private LocalDate endDate;

    private LocalDate finishDate;

    public TaskDTO() {
    }

    public TaskDTO(Long id, String name, String description, LocalDate endDate, LocalDate finishDate) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.endDate = endDate;
        this.finishDate = finishDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public LocalDate getFinishDate() {
        return finishDate;
    }

    public void setFinishDate(LocalDate finishDate) {
        this.finishDate = finishDate;
    }
}
