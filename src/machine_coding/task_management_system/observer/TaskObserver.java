package machine_coding.task_management_system.observer;

import machine_coding.task_management_system.entities.Task;

public interface TaskObserver {
    void update(Task task, String changeType);
}
