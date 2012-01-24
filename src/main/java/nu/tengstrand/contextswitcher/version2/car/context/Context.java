package nu.tengstrand.contextswitcher.version2.car.context;

public class Context {
    private UserRole userRole;

    public Context(UserRole userRole) {
        this.userRole = userRole;
    }

    public boolean isRenaultManager() {
        return userRole == UserRole.MANAGER;
    }
}
