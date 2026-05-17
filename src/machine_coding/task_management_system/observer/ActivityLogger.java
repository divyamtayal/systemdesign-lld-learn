package machine_coding.task_management_system.observer;

import machine_coding.task_management_system.entities.Task;

public class ActivityLogger implements TaskObserver {
    @Override
    public void update(Task task, String changeType) {
        System.out.println("LOGGER: Task '" + task.getTitle() + "' was updated. Change: " + changeType);
    }
}