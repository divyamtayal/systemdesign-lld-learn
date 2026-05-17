package machine_coding.task_management_system.strategy;

import java.util.Comparator;
import java.util.List;

import machine_coding.task_management_system.entities.Task;

public class SortByPriority implements TaskSortStrategy {
    @Override
    public void sort(List<Task> tasks) {
        // Higher priority (lower enum ordinal) comes first
        tasks.sort(Comparator.comparing(Task::getPriority).reversed());
    }
}