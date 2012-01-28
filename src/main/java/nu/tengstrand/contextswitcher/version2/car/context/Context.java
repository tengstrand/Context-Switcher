package nu.tengstrand.contextswitcher.version2.car.context;

public class Context {
    private UserRole userRole;

    public Context() {
        userRole = UserRole.DEFAULT;
    }

    public Context(UserRole userRole) {
        this.userRole = userRole;
    }

    public boolean hasRightsToReadColor() {
        return userRole != UserRole.RESTRICTED;
    }
}
