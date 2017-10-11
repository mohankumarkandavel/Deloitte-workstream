package nz.co.vincens.model;

/**
 * A generic user in the system. There are two users with different privileges: a {@link TeamMember} and a
 * {@link Manager}.
 */
public class User {

    private String username;
    private String name;
    private String email;
    private String id;
    private String role;
    private String password;

	// default constructor required by Spring
	public User() {

	}

    public User(String id, String username, String name, String email, String role, String password) {
        this.id = id;
        this.username = username;
        this.name = name;
        this.email = email;
        this.role = role;
        this.password = password;
    }

	public String getUsername() {
        return username;
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

    public String getRole() {
        return role;
    }

    public String getPassword() {
        return password;
    }
}
