package nz.co.vincens.tasks;

import nz.co.vincens.model.Attribute;
import nz.co.vincens.model.Group;
import nz.co.vincens.model.Task;
import nz.co.vincens.model.TeamMember;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * API endpoint for accessing {@link Ranker}
 */
@CrossOrigin
@RestController
public class RankerController {

	private List<TeamMember> teamMembers;

	public RankerController() {
		teamMembers = new ArrayList<>();

		HashMap<Group, Attribute> weightings = new HashMap<>();
		weightings.put(Group.BUSINESS_DEVELOPMENT, new Attribute(1,2,3,4));
		teamMembers.add(new TeamMember("Amy Lin", "xlin504", "1", weightings));

		weightings.put(Group.BUSINESS_DEVELOPMENT, new Attribute(1,2,3,4));
		teamMembers.add(new TeamMember("Kelvin Lau", "klau158", "2", weightings));
	}

	@RequestMapping("/rank")
	private @ResponseBody List<TeamMember> getEmployees(@RequestBody Task task) {
		Ranker ranker = new Ranker();
		return ranker.findBestTeamMembers(task, teamMembers);
	}
}
