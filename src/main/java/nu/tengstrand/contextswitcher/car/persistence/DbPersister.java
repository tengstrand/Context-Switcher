package nu.tengstrand.contextswitcher.car.persistence;

import nu.tengstrand.contextswitcher.car.CarContextSwitcher;

public class DbPersister {
    public Integer save(Integer primaryKey, CarContextSwitcher carContextSwitcher) {
        // We are faking that the incoming PK is null and that the primary key for the
        // newly created instance is 12.
        return 12;
    }
}
