package nu.tengstrand.contextswitcher.version2.car.context;

public class User {
    private Role role;

    public User(Role role) {
        this.role = role;
    }

    public Role getRole() {
        return role;
    }
}
