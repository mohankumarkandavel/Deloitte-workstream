package nz.co.vincens.model;

import java.util.HashMap;

/**
 * A team member which may be assigned to nz.co.vincens.tasks.
 */
public class TeamMember extends User {

	private HashMap<Group, Attribute> weightings;
	private Task[] tasks;

	public TeamMember(String name, String email, String id) {
		super(name, email, id);
	}

	public HashMap<Group, Attribute> getWeightings() {
		return weightings;
	}

}
