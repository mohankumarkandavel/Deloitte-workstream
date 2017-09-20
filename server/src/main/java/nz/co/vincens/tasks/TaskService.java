package nz.co.vincens.tasks;

import nz.co.vincens.model.Attribute;
import nz.co.vincens.model.Group;
import nz.co.vincens.model.Status;
import nz.co.vincens.model.Task;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * A service which provides access to tasks
 */
@Service
public class TaskService {

    private List<Task> tasks;

    public TaskService() {
        this.tasks = new ArrayList<>();
        tasks.add(new Task(1, "Task One", "Description One", new Attribute(3, 3, 3, 3), new Date(), Group
                .HUMAN_CAPITAL, Status.PENDING, 1));
        tasks.add(new Task(2, "Task Two", "Description Two", new Attribute(1, 4, 2, 5), new Date(), Group
                .SOFTWARE, Status.ASSIGNED, 2));
    }

    /**
     * @return all tasks
     */
    public List<Task> getTasks() {
        return tasks;
    }

    /**
     * @param id the task id
     * @return task with specified id
     */
    public Task getTask(int id) {
        return tasks.get(id);
    }

    /**
     * Adds the specified task
     *
     * @param task task to add
     */
    public void addTask(Task task) {
        tasks.add(task);
    }
}
