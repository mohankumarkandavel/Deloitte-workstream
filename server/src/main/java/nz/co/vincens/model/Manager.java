package nz.co.vincens.model;

/**
 * A manager can create new nz.co.vincens.tasks and assign nz.co.vincens.tasks to {@link TeamMember}.
 */
public class Manager extends User {
	public Manager(String name, String email, String id) {
		super(name, email, id);
	}
}
