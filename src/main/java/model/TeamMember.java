package model;

import java.util.HashMap;

/**
 * A team member which may be assigned to tasks.
 */
public class TeamMember extends User {

	private HashMap<Group, Integer> weightings;
	private Task[] tasks;

	public TeamMember(String name, String email, String id) {
		super(name, email, id);
	}
}
