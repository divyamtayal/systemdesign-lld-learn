package machine_coding.task_management_system.strategy;

import java.util.List;

import machine_coding.task_management_system.entities.Task;

public interface TaskSortStrategy {
    void sort(List<Task> tasks);
}