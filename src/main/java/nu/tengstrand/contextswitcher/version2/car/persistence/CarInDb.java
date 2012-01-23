package nu.tengstrand.contextswitcher.version2.car.persistence;

import nu.tengstrand.contextswitcher.version2.car.state.CarState;
import nu.tengstrand.contextswitcher.version2.car.state.CarStateSwitcher;

public class CarInDb {
    private final CarState state;

    public CarInDb(CarState carState) {
        state = carState;
    }

    public boolean isPersisted() {
        return state.primaryKey != null;
    }

    public CarInDb save(DbPersister dbPersister) {
        dbPersister.save(state);
        return this;
    }

    public CarStateSwitcher as() {
        return new CarStateSwitcher(state);
    }

    /**
     * Take the primary key into account when comparing objects.
     */
    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;

        return state.equals(((CarInDb)o).state);
    }

    /**
     * Take the primary key into account when calculating hash code.
     */
    @Override
    public int hashCode() {
        return state.hashCode();
    }

    @Override
    public String toString() {
        return "CarInDb{primaryKey=" + state.primaryKey + ", " + state + '}';
    }
}
