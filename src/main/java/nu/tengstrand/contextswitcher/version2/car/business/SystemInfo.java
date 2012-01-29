package nu.tengstrand.contextswitcher.version2.car.business;

import nu.tengstrand.contextswitcher.version2.car.context.SystemVersion;

public class SystemInfo {
    private SystemVersion system;

    public SystemInfo(SystemVersion system) {
        this.system = system;
    }

    @Override
    public String toString() {
        return system == SystemVersion.ENTERPRISE ? "#Enterprise# " : "";
    }
}
