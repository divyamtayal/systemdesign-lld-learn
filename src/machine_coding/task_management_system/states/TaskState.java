package machine_coding.task_management_system.states;

import machine_coding.task_management_system.entities.Task;
import machine_coding.task_management_system.enums.TaskStatus;

public interface TaskState {
    void startProgress(Task task);

    void completeTask(Task task);

    void reopenTask(Task task);

    TaskStatus getStatus();
}