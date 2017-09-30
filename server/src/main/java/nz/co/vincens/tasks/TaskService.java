package nz.co.vincens.tasks;

import nz.co.vincens.login.UserService;
import nz.co.vincens.model.Attribute;
import nz.co.vincens.model.Group;
import nz.co.vincens.model.Manager;
import nz.co.vincens.model.Status;
import nz.co.vincens.model.Task;
import nz.co.vincens.model.TeamMember;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * A service which provides access to tasks
 */
@Service
public class TaskService {

    private List<Task> tasks;
    @Autowired
    private UserService userService;

    public TaskService() {
    }

    @PostConstruct
    private void init() {
        this.tasks = new ArrayList<>();
        Manager manager = (Manager) userService.getUsers().get(0);
        TeamMember teamMember = (TeamMember) userService.getUser(2);
        tasks.add(new Task(tasks.size() + 1, "Task One", "Description One", new Attribute(3, 3, 3, 3), new Date(),
                Group.HUMAN_CAPITAL, Status.DRAFT, 1, manager));

        Task task = new Task(tasks.size() + 1, "Task Two", "Description Two", new Attribute(1, 4, 2, 5), new Date(),
                Group.SOFTWARE, Status.ASSIGNED, 2, manager);
        task.addAssignee(teamMember);
        tasks.add(task);

        task = new Task(tasks.size() + 1, "Task tree", "Description of a tree", new Attribute(2, 5, 3, 1), new Date()
                , Group.HUMAN_CAPITAL, Status.PENDING, 1, manager);
        task.addRequestedAssignee(teamMember);
        task.addRequestedAssignee((TeamMember) userService.getUser(3));
        tasks.add(task);
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
        for( Task task: tasks) {
            if (task.getId() == id) return task;
        }
        return null;
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
