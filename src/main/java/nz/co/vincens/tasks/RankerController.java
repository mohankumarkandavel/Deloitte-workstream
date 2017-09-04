package nz.co.vincens.tasks;

import nz.co.vincens.model.Attribute;
import nz.co.vincens.model.Group;
import nz.co.vincens.model.Task;
import nz.co.vincens.model.TeamMember;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * API endpoint for accessing {@link Ranker}
 */
@RestController
public class RankerController {

	@RequestMapping("/rank")
	private void getEmployees(Task task) {
		Ranker ranker = new Ranker();
		HashMap<Group, Attribute> weightings = new HashMap<>();
		weightings.put(Group.BUSINESS_DEVELOPMENT, new Attribute(1,2,3,4));
		TeamMember teamMember = new TeamMember("Amy Lin", "xlin504", "1", weightings);
		weightings.put(Group.BUSINESS_DEVELOPMENT, new Attribute(1,2,3,4));
		TeamMember teamMember2 = new TeamMember("Kelvin Lau", "klau158", "2", weightings);
		List<TeamMember> teamMembers = new ArrayList<>();
		teamMembers.add(teamMember);
		teamMembers.add(teamMember2);
		ranker.findBestTeamMembers(task, teamMembers);
	}
}
