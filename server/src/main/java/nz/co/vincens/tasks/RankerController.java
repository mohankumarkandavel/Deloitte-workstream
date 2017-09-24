package nz.co.vincens.tasks;

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

    private List<TeamMember> teamMembers;

    @Autowired
    private TaskService taskService;

    public RankerController() {
        teamMembers = new ArrayList<>();

        HashMap<Group, Attribute> weightings = new HashMap<>();
        weightings.put(Group.BUSINESS_AND_DEVELOPMENT, new Attribute(4, 2, 3, 4));
        weightings.put(Group.HUMAN_CAPITAL, new Attribute(1, 2, 3, 4));
        teamMembers.add(new TeamMember("Amy Lin", "xlin504", "xlin504@aucklanduni.ac.nz", "1", weightings));

        weightings = new HashMap<>();
        weightings.put(Group.BUSINESS_AND_DEVELOPMENT, new Attribute(1, 1, 2, 2));
        weightings.put(Group.HUMAN_CAPITAL, new Attribute(1, 2, 3, 4));
        teamMembers.add(new TeamMember("Kelvin Lau", "klau158", "klau158@aucklanduni.ac.nz","2", weightings));

        weightings = new HashMap<>();
        weightings.put(Group.BUSINESS_AND_DEVELOPMENT, new Attribute(3, 2, 4, 3));
        weightings.put(Group.HUMAN_CAPITAL, new Attribute(1, 2, 3, 4));
        teamMembers.add(new TeamMember("Bowen Wu", "bowen", "klau158@aucklanduni.ac.nz","3", weightings));

        weightings = new HashMap<>();
        weightings.put(Group.BUSINESS_AND_DEVELOPMENT, new Attribute(1, 3, 5, 4));
        weightings.put(Group.HUMAN_CAPITAL, new Attribute(1, 2, 3, 4));
        teamMembers.add(new TeamMember("Shenghong Huang", "shenghong", "klau158@aucklanduni.ac.nz", "4", weightings));

        weightings = new HashMap<>();
        weightings.put(Group.BUSINESS_AND_DEVELOPMENT, new Attribute(2, 4, 3, 2));
        weightings.put(Group.HUMAN_CAPITAL, new Attribute(1, 2, 3, 4));
        teamMembers.add(new TeamMember("Rick Ying", "rick", "\"klau158@aucklanduni.ac.nz","5", weightings));

        weightings = new HashMap<>();
        weightings.put(Group.BUSINESS_AND_DEVELOPMENT, new Attribute(4, 3, 1, 4));
        weightings.put(Group.HUMAN_CAPITAL, new Attribute(1, 2, 3, 4));
        teamMembers.add(new TeamMember("Mohan Kumar", "mohan", "klau158@aucklanduni.ac.nz","6", weightings));

        weightings = new HashMap<>();
        weightings.put(Group.BUSINESS_AND_DEVELOPMENT, new Attribute(5, 5, 5, 5));
        weightings.put(Group.HUMAN_CAPITAL, new Attribute(1, 2, 3, 4));
        teamMembers.add(new TeamMember("Fraser Lewis-Smith", "fralewsmi", "klau158@aucklanduni.ac.nz","7", weightings));
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
        return ranker.findBestTeamMembers(taskService.getTask(taskId - 1), teamMembers);
    }
}
