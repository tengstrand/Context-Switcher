package nu.tengstrand.contextswitcher.version2.car.persistence;

import nu.tengstrand.contextswitcher.version2.car.state.CarState;
import nu.tengstrand.contextswitcher.version2.car.export.CarAsRow;

public class CarInDb {
    private final CarState state;

    public CarInDb(CarState carState) {
        // Make sure the state is valid and that we have no references to the outside world!
        state = carState.ensureValidState();
    }

    public boolean isPersisted() {
        return state.primaryKey != null;
    }

    public CarInDb save(Database database) {
        if (state.primaryKey == null) {
            state.primaryKey = database.getNextCarPrimaryKey();
        }
        System.out.println("Saved to database: " + this);

        return this;
    }

    public CarAsRow asCarAsRow() {
        return new CarAsRow(state);
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
