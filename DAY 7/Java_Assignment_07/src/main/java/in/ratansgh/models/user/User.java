package in.ratansgh.models.user;

import in.ratansgh.models.enums.UserRole;

public abstract class User {
    protected String id;
    protected String name;
    protected UserRole role;

    public User(String id, String name, UserRole role) {
        this.id = id;
        this.name = name;
        this.role = role;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public UserRole getRole() {
        return role;
    }
}