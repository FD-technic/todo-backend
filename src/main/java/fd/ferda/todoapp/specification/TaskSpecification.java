package fd.ferda.todoapp.specification;

import fd.ferda.todoapp.entity.TaskEntity;
import fd.ferda.todoapp.filter.TaskFilter;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class TaskSpecification {

    public static Specification<TaskEntity> build(TaskFilter filter, LocalDate today) {
        return (root, query, cb) -> {

            List<Predicate> predicates = new ArrayList<>();

            if(filter.getName() != null && !filter.getName().isBlank()) {
                predicates.add(cb.like(
                        cb.lower(root.get("name")),
                        "%" + filter.getName().toLowerCase() + "%"));
            }

            if(filter.getDescription() != null && !filter.getDescription().isBlank()) {
                predicates.add(cb.like(
                        cb.lower(root.get("description")),
                        "%" + filter.getDescription().toLowerCase() + "%"));
            }

            if(filter.getEndDateFrom() != null) {
                predicates.add(cb.greaterThanOrEqualTo(root.get("endDate"), filter.getEndDateFrom()));
            }

            if(filter.getEndDateTo() != null) {
                predicates.add(cb.lessThanOrEqualTo(root.get("endDate"), filter.getEndDateTo()));
            }

            if(Boolean.TRUE.equals(filter.getOverdueTask())) {
                predicates.add(cb.lessThan(root.get("endDate"), today));
                predicates.add(cb.isNull(root.get("finishDate")));
            } else if(filter.getFinished() != null) {
                if(!filter.getFinished()) {
                    predicates.add(cb.isNull(root.get("finishDate")));
                } else {
                    predicates.add(cb.isNotNull(root.get("finishDate")));
                }
            }

            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }
}
