package nz.co.vincens.tasks;

import nz.co.vincens.model.Status;
import nz.co.vincens.model.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

/**
 * API endpoints for accessing and managing nz.co.vincens.tasks
 */
//@CrossOrigin(origins = "http:localhost:4200")
@RestController
public class TaskController {

    @Autowired
    TaskService taskService;

	@CrossOrigin
    @RequestMapping("/task")
    List<Task> getTasks() {
        return taskService.getTasks();
    }

	@CrossOrigin
    @RequestMapping("/task/{id}")
    Task getTask(@PathVariable int id) {
        return taskService.getTask(id - 1);
    }

	@CrossOrigin
    @RequestMapping(value = "/task", method = RequestMethod.POST)
    ResponseEntity<?> addTask(@RequestBody Task task) {
        task.setId(taskService.getTasks().size() + 1);
        task.setStatus(Status.DRAFT);
        taskService.addTask(task);
        try {
            return ResponseEntity.created(new URI("/task/" + task.getId())).build();
        } catch (URISyntaxException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

}
