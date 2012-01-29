package nu.tengstrand.contextswitcher.version2.car.context;

/**
 * Immutable object that can represent the current context
 * used by the CarFactory to choose implementation.
 */
public class Context {
    public final User user;
    public final SystemVersion systemVersion;

    public Context(SystemVersion systemVersion, User user) {
        this.systemVersion = systemVersion;
        this.user = user;
    }

    public boolean hasRightsToReadColor() {
        return user.getRole() != Role.RESTRICTED;
    }

    public Context as(User user) {
        return new Context(systemVersion, user);
    }

    public Context in(SystemVersion systemVersion) {
        return new Context(systemVersion, user);
    }
}
