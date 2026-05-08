package fd.ferda.todoapp.filter;

import java.time.LocalDate;

public class TaskFilter {

    private String name = null;
    private String description = null;
    private LocalDate endDateFrom = null;
    private LocalDate endDateTo = null;
    private Boolean overdueTask = null;
    private Boolean finished = null;
    private Integer page = 0;
    private Integer pageSize = 30;

    public TaskFilter() {}

    public TaskFilter(String name, String description, LocalDate endDateFrom, LocalDate endDateTo, Boolean overdueTask, Boolean finished, Integer page, Integer pageSize) {
        this.name = name;
        this.description = description;
        this.endDateFrom = endDateFrom;
        this.endDateTo = endDateTo;
        this.overdueTask = overdueTask;
        this.finished = finished;
        this.page = page;
        this.pageSize = pageSize;
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

    public LocalDate getEndDateFrom() {
        return endDateFrom;
    }

    public void setEndDateFrom(LocalDate endDateFrom) {
        this.endDateFrom = endDateFrom;
    }

    public LocalDate getEndDateTo() {
        return endDateTo;
    }

    public void setEndDateTo(LocalDate endDateTo) {
        this.endDateTo = endDateTo;
    }

    public Boolean getOverdueTask() {
        return overdueTask;
    }

    public void setOverdueTask(Boolean overdueTask) {
        this.overdueTask = overdueTask;
    }

    public Boolean getFinished() {
        return finished;
    }

    public void setFinished(Boolean finished) {
        this.finished = finished;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }
}
