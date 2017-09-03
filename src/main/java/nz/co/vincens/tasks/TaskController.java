package nz.co.vincens.tasks;

import nz.co.vincens.model.Attribute;
import nz.co.vincens.model.Group;
import nz.co.vincens.model.Status;
import nz.co.vincens.model.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * API endpoints for accessing and managing nz.co.vincens.tasks
 */
@RestController
public class TaskController {

    private List<Task> tasks;

    public TaskController() {
        this.tasks = new ArrayList<>();
        tasks.add(new Task(1, "Task One", "Description1", new Attribute(3, 3, 3, 3), new Date(), Group
                .HUMAN_CAPITAL, Status.PENDING, 1, 3));
        tasks.add(new Task(2, "Task Two", "Description2", new Attribute(1, 4, 2, 5), new Date(), Group
                .SOFTWARE, Status.ASSIGNED, 2, 2));
    }

    @RequestMapping("/tasks")
    List<Task> getTasks() {
        return tasks;
    }

    @RequestMapping("/task/{id}")
    Task getTask(@PathVariable int id) {
        return tasks.get(id - 1);
    }

    @RequestMapping(value = "/task", method = RequestMethod.POST)
    ResponseEntity<?> addNewTask(@RequestBody Task task) {
        task.setId(tasks.size() + 1);
        tasks.add(task);
        try {
            return ResponseEntity.created(new URI("/task/" + task.getId())).build();
        } catch (URISyntaxException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

}
