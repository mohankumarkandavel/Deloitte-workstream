package nz.co.vincens.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * A team member which may be assigned to nz.co.vincens.tasks.
 */
public class TeamMember extends User {

	private Map<Group, Attribute> weightings;
	private List<Task> tasks = new ArrayList<>();

	public TeamMember() {
		super();
	}

	public TeamMember(String name, String username, String email, String id, Map<Group, Attribute> weightings, String
					  password) {
		super(id, username, name, email, "team-member", password);
		this.weightings = weightings;
	}

	public Map<Group, Attribute> getWeightings() {
		return weightings;
	}

	public List<Task> getTasks() {
		return tasks;
	}
}
