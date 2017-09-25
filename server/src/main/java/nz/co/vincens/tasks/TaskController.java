package nz.co.vincens.tasks;

import nz.co.vincens.model.Status;
import nz.co.vincens.model.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.stream.Collectors;

/**
 * API endpoints for accessing and managing nz.co.vincens.tasks
 * <code>
 * <ul>
 * <li>GET  /task</li>
 * <li>GET  /task/{id}</li>
 * <li>POST /task</li>
 * <li>PUT  /task</li>
 * </ul>
 * </code>
 */
@RestController
public class TaskController {

    @Autowired private TaskService taskService;

    /**
     * Endpoint: <code>GET /task</code>
     * <br/>
     * Gets all tasks, including completed
     *
     * @return all tasks as JSON
     */
    @CrossOrigin
    @RequestMapping("/task/{userId}")
    List<Task> getTasks(@PathVariable(name = "userId") int userId) {
        return taskService.getTasks().stream()
                .filter(task -> task.getAssignees().stream()
                        .anyMatch(assignee -> assignee.getId().equals(String.valueOf(userId)))
                        || task.getOwner().getId().equals(String.valueOf(userId)))
                .collect(Collectors.toList());
    }

    /**
     * Endpoint: <code>GET /task/{id}</code>
     * <br/>
     * Gets a task by the specified id
     *
     * @return task with the specified id as JSON
     */
    @CrossOrigin
    @RequestMapping("/task/{userId}/{id}")
    Task getTask(@PathVariable int userId, @PathVariable int id) {
        return taskService.getTask(id - 1);
    }

    /**
     * Endpoint: <code>POST /task</code>
     * <br/>
     * Adds a tasks specified by the request body, JSON format of a task
     *
     * @return 201 created response if successful, with Location header, otherwise server error
     */
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

    /**
     * Endpoint: <code>PUT /task</code>
     * Updates or creates the task specified with the JSON body
     *
     * @param task {@link Task} in JSON format
     * @return 200 ok if the task is added or updated
     */
    @CrossOrigin(methods = RequestMethod.PUT)
    @RequestMapping(value = "/task", method = RequestMethod.PUT)
    ResponseEntity<?> updateTask(@RequestBody Task task) {
        taskService.getTask(task.getId() - 1).setStatus(Status.PENDING);
        return ResponseEntity.ok().build();
    }

}
