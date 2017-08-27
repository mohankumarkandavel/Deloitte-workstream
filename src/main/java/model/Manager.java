package model;

/**
 * A manager can create new tasks and assign tasks to {@link TeamMember}.
 */
public class Manager extends User {
	public Manager(String name, String email, String id) {
		super(name, email, id);
	}
}
