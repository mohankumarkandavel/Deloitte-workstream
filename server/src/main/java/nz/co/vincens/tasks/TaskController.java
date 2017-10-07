package nz.co.vincens.tasks;

import nz.co.vincens.login.UserService;
import nz.co.vincens.model.Status;
import nz.co.vincens.model.Task;
import nz.co.vincens.model.TeamMember;
import nz.co.vincens.service.TaskHelper;
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

    @Autowired
    private TaskService taskService;
    @Autowired
    private UserService userService;

    /**
     * Endpoint: <code>GET /task</code>
     * <br/>
     * Gets all tasks, including completed
     * 加载所有的任务，包含已经完成的
     *
     * @return all tasks as JSON
     */
    @CrossOrigin
    @RequestMapping("/task/{userId}")
    List<Task> getTasks(@PathVariable(name = "userId") int userId) {
        System.out.printf("111111111");
        // 通过当前登录用户的ID，查找所有task
        // 通过ID，获取Manager所建立的task
        // 通过ID，获取Assignees所被分配的task
        // task包含所有状态(status)的
        // for manager, find tasks that they own, for assignees, find tasks that are either requested or assigned
        List<Task> tasks = new ArrayList<>();
        for (Task task : taskService.getTasks()) {
            // 在【虚假数据】中，通过ID找到Manager的task
            if (task.getOwner().getId().equals(String.valueOf(userId))) {
                tasks.add(task);
            } else if (!task.getStatus().equals(Status.DRAFT)) { // 获取非Draft任务
                // 在【虚假数据】中，通过ID找到Assignees的task
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
     * 加载指定ID的任务
     *
     * @return task with the specified id as JSON
     */
    @CrossOrigin
    @RequestMapping("/task/{userId}/{id}")
    Task getTask(@PathVariable int userId, @PathVariable int id) {
        System.out.printf("222222222");
        return taskService.getTask(id);
    }

    /**
     * Endpoint: <code>POST /task</code>
     * <br/>
     * Adds a tasks specified by the request body, JSON format of a task
     * 仅限于manager
     * 增加一个任务
     * 添加后，重新加载全部任务（执行11111111）
     *
     * @return 201 created response if successful, with Location header, otherwise server error
     */
    @CrossOrigin
    @RequestMapping(value = "/task", method = RequestMethod.POST)
    ResponseEntity<?> addTask(@RequestBody Task task) {
        System.out.printf("3333333333");
        task.setId(taskService.getTasks().size() + 1);
        task.setStatus(Status.DRAFT);
        System.out.println(task.getName());
        System.out.println(task.getDescription());
        System.out.println(task.getDeadline());
        System.out.println(task.getGroup());
        System.out.println(task.getResources());
        System.out.println(task.getAvailability());
        System.out.println(task.getNumAssigneesRequired());
        System.out.println(task.getStatus());
        TaskHelper.addTask(task,1);
//        taskService.addTask(task);
        try {
            return ResponseEntity.created(new URI("/task/" + task.getId())).build();
        } catch (URISyntaxException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    /**
     * Endpoint: <code>PUT /task</code>
     * Updates or creates the task specified with the JSON body
     * 修改任务，把draft移动到pending
     * 刷新任务（执行11111）
     * 为任务分配员工（执行555555555）
     *
     * @param task {@link Task} in JSON format
     * @return 200 ok if the task is added or updated
     */
    @CrossOrigin(methods = RequestMethod.PUT)
    @RequestMapping(value = "/task", method = RequestMethod.PUT)
    ResponseEntity<?> updateTaskStatus(@RequestBody Task task) {
        System.out.printf("44444444444");
        taskService.getTask(task.getId()).setStatus(task.getStatus());
        return ResponseEntity.ok().build();
    }

    // 为任务分配员工
    @CrossOrigin
    @RequestMapping(value = "/task/request-assignee/{taskId}", method = RequestMethod.PUT)
    ResponseEntity<?> sendInviteToSelectedTeamMembers(@PathVariable int taskId, @RequestBody String userId) {
        System.out.printf("55555555555555");
        TeamMember teamMember = (TeamMember) userService.getUser(Integer.valueOf(userId));
        taskService.getTask(taskId).addRequestedAssignee(teamMember);
        return ResponseEntity.ok().build();
    }

    // 更新任务分配的员工
    @CrossOrigin
    @RequestMapping(value = "/task/add-assignee/{taskId}", method = RequestMethod.PUT)
    ResponseEntity<?> updateTaskAssignees(@PathVariable int taskId, @RequestBody String userId) {
        System.out.printf("666666666666");
        TeamMember teamMember = (TeamMember) userService.getUser(Integer.valueOf(userId));
        taskService.getTask(taskId).addAssignee(teamMember);
        return ResponseEntity.ok().build();
    }

}
