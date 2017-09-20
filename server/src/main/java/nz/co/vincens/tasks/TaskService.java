package nz.co.vincens.tasks;

import nz.co.vincens.model.Attribute;
import nz.co.vincens.model.Group;
import nz.co.vincens.model.Status;
import nz.co.vincens.model.Task;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class TaskService {

    private List<Task> tasks;

    public TaskService() {
        this.tasks = new ArrayList<>();
        tasks.add(new Task(1, "Task One", "Description1", new Attribute(3, 3, 3, 3), new Date(), Group
                .HUMAN_CAPITAL, Status.PENDING, 1));
        tasks.add(new Task(2, "Task Two", "Description2", new Attribute(1, 4, 2, 5), new Date(), Group
                .SOFTWARE, Status.ASSIGNED, 2));
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public Task getTask(int id) {
        return tasks.get(id);
    }

    public void addTask(Task task) {
        tasks.add(task);
    }
}
