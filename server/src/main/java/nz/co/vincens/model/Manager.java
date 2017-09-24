package nz.co.vincens.model;

/**
 * A manager can create new nz.co.vincens.tasks and assign nz.co.vincens.tasks to {@link TeamMember}.
 */
public class Manager extends User {
	public Manager(String id, String username, String name, String email) {
		super(id, username, name, email, "manager");
	}

	public Manager(String id, String username, String name, String email, String password) {
		super(id, username, name, email, "manager", password);
	}
}
