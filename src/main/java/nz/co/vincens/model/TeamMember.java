package nz.co.vincens.model;

import java.util.HashMap;
import java.util.List;

/**
 * A team member which may be assigned to nz.co.vincens.tasks.
 */
public class TeamMember extends User {

	private HashMap<Group, Attribute> weightings;
	private List<Task> tasks;

	public TeamMember(String name, String email, String id, HashMap<Group, Attribute> weightings) {
		super(name, email, id);
		this.weightings = weightings;
	}

	public HashMap<Group, Attribute> getWeightings() {
		return weightings;
	}

	public List<Task> getTasks() {
		return tasks;
	}
}
