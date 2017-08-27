package model;

import java.util.HashMap;

/**
 * A team member which may be assigned to tasks.
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

	public class Attribute {
		int experience;
		int interest;
		int availability;
		int resource;

		public int getExperience() {
			return experience;
		}

		public int getInterest() {
			return interest;
		}

		public int getAvailability() {
			return availability;
		}

		public int getResource() {
			return resource;
		}
	}
}
