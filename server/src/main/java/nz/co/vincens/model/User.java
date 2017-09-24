package nz.co.vincens.model;

/**
 * A generic user in the system. There are two users with different privileges: a {@link TeamMember} and a
 * {@link Manager}.
 */
public class User {

    String username;
    String name;
    String email;
    String id;
    String role;
    String password;

    public User(String id, String username, String name, String email, String role, String password) {
        this.id = id;
        this.username = username;
        this.name = name;
        this.email = email;
        this.role = role;
        this.password = password;
    }

    public User(String id, String username, String name, String email, String role) {
        this.id = id;
        this.username = username;
        this.name = name;
        this.email = email;
        this.role = role;
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
