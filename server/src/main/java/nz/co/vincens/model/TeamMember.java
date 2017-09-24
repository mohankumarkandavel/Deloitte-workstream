package nz.co.vincens.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * A team member which may be assigned to nz.co.vincens.tasks.
 */
public class TeamMember extends User {

	private HashMap<Group, Attribute> weightings;
	private List<Task> tasks = new ArrayList<>();

	public TeamMember(String name, String username, String email, String id, HashMap<Group, Attribute> weightings) {
		super(id, username, name, email, "team-member");
		this.weightings = weightings;
	}

	public TeamMember(String id, String username, String name, String email, String password) {
		super(id, username, name, email, "team-member", password);
		this.weightings = weightings;
	}

	public HashMap<Group, Attribute> getWeightings() {
		return weightings;
	}

	public List<Task> getTasks() {
		return tasks;
	}
}
