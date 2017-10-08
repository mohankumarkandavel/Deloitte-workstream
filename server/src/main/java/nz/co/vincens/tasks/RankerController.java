package nz.co.vincens.tasks;

import nz.co.vincens.login.UserService;
import nz.co.vincens.model.Attribute;
import nz.co.vincens.model.Group;
import nz.co.vincens.model.TeamMember;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * API endpoint for accessing {@link Ranker}
 * <code>
 * <ul>
 * <li>GET /rank/{id}</li>
 * </ul>
 * </code>
 */
@CrossOrigin
@RestController
public class RankerController {

    @Autowired private TaskService taskService;
    @Autowired private UserService userService;

    public RankerController() {
    }

    /**
     * Endpoint: <code>GET /rank/{id}</code>
     * <br/>
     * Finds the best team member for the specified task (from id)
     *
     * @param taskId the task id
     * @return ordered and filtered list of best employees
     */
    @RequestMapping("/rank/{task_id}")
    private @ResponseBody
    List<TeamMember> getEmployees(@PathVariable(name = "task_id") int taskId) {
        Ranker ranker = new Ranker();
        return ranker.findBestTeamMembers(taskService.getTask(taskId), userService.getTeamMembers());
    }
}
