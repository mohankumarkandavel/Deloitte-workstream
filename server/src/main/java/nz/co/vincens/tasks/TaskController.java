package nz.co.vincens.tasks;

import nz.co.vincens.login.UserService;
import nz.co.vincens.model.Status;
import nz.co.vincens.model.Task;
import nz.co.vincens.model.TeamMember;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

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
    @Autowired private UserService userService;

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

		// for manager, find tasks that they own, for assignees, find tasks that are either requested or assigned
		List<Task> tasks = new ArrayList<>();
		for (Task task : taskService.getTasks()) {
			if (task.getOwner().getId().equals(String.valueOf(userId))) {
				tasks.add(task);
			} else if (!task.getStatus().equals(Status.DRAFT)) {
					for (int i = 0; i < task.getAssignees().size(); i++) {
						if (task.getAssignees().get(i).getId().equals(String.valueOf(userId))) {
							tasks.add(task);
						}
					}
					for (int i = 0; i < task.getRequestedAssignees().size(); i++) {
						if (task.getRequestedAssignees().get(i).getId().equals(String.valueOf(userId))) {
							tasks.add(task);
						}
					}
			}
		}
		return tasks;
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
        return taskService.getTask(id);
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
    ResponseEntity<?> updateTaskStatus(@RequestBody Task task) {
        taskService.getTask(task.getId()).setStatus(task.getStatus());
        if (task.getReasonForDeclining() != null) {
			if (task.getReasonForDeclining().length() != 0) {
				taskService.getTask(task.getId()).setReasonForDeclining(task.getReasonForDeclining());
				taskService.getTask(task.getId()).addDeclinedAssignee(task.getRequestedAssignees().get(0));
				taskService.getTask(task.getId()).removeRequestedAssignee(task.getRequestedAssignees().get(0));
			}
		}
        return ResponseEntity.ok().build();
    }

    /**
     * Endpoint: <code>PUT /task/{taskId}/request-assignee</code>
     * @param taskId The id of the {@link Task}
     * @param requesteeIds The list of ids of the {@link nz.co.vincens.model.User}s who have been requested for the
     *                     task
     * @return 200 ok if the task is added or updated
     */
    @CrossOrigin
    @RequestMapping(value = "/task/{taskId}/request-assignee", method = RequestMethod.PUT)
    ResponseEntity<?> sendInviteToSelectedTeamMembers(@PathVariable int taskId, @RequestBody List<Integer>
            requesteeIds) {
        Task task = taskService.getTask(taskId);
        for (int id: requesteeIds) {
            TeamMember teamMember = (TeamMember) userService.getUser(Integer.valueOf(id));
            task.addRequestedAssignee(teamMember);
        }
        return ResponseEntity.ok().build();
    }

    /**
     * Endpoint: <code>PUT /task/add-assignee/{taskId}</code>
     * @param taskId The id of the {@link Task}
     * @param userId The id of the {@link nz.co.vincens.model.User} assigned to the task
     * @return 200 ok if the task is added or updated
     */
    @CrossOrigin
	@RequestMapping(value = "/task/add-assignee/{taskId}", method = RequestMethod.PUT)
	ResponseEntity<?> updateTaskAssignees(@PathVariable int taskId, @RequestBody String userId) {
		TeamMember teamMember = (TeamMember) userService.getUser(Integer.parseInt(userId));
		taskService.getTask(taskId).addAssignee(teamMember);
		return ResponseEntity.ok().build();
	}

    /**
     * Endpoint: <code>PUT /task/{taskId}/requestsById</code>
     * Specifies that the given user has requested more information for the given task
     * @param taskId the id of the task
     * @param userId the id of the user
     * @return 303 See Other, URI location is the task that has been modified
     * @throws URISyntaxException if the URI location is invalid
     */
	@CrossOrigin
    @RequestMapping(value = "/task/{taskId}/requestsById", method = RequestMethod.PUT)
    ResponseEntity<?> requestMoreInformation(@PathVariable int taskId, @RequestBody String userId) throws
            URISyntaxException {
        taskService.getTask(taskId).requestMoreInfo(Integer.parseInt(userId));
        return ResponseEntity.status(HttpStatus.SEE_OTHER).location(new URI("/task/" + taskId)).build();
    }

}
