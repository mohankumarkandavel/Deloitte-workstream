package model;

/**
 * A generic user in the system. There are two users with different privileges: a {@link TeamMember} and a
 * {@link Manager}.
 */
public class User {

	String name;
	String email;
	String id;

	public User(String name, String email, String id) {
		this.name = name;
		this.email = email;
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public String getEmail() {
		return email;
	}

	public String getId() {
		return id;
	}
}
