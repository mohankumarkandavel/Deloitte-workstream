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

	public TeamMember(String name, String username, String email, String id, Map<Group, Attribute> weightings) {
		super(id, username, name, email, "team-member");
		this.weightings = weightings;
	}

	public TeamMember(String id, String username, String name, String email, String password) {
		super(id, username, name, email, "team-member", password);
	}

	public TeamMember(String id, String username, String name, String email, String role, String password, Map<Group,
			Attribute> weightings, List<Task> tasks) {
		super(id, username, name, email, role, password);
		this.weightings = weightings;
		this.tasks = tasks;
	}

	public Map<Group, Attribute> getWeightings() {
		return weightings;
	}

	public List<Task> getTasks() {
		return tasks;
	}
}
