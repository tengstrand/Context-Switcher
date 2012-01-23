package nu.tengstrand.contextswitcher.version2.car.persistence;

import nu.tengstrand.contextswitcher.version2.car.state.CarState;

public class DbPersister {
    private int primaryKey = 1;

    /**
     * Saves the car state to the database!
     */
    public void save(CarState state) {
        if (state.primaryKey == null) {
            state.primaryKey = primaryKey++;
        }
        System.out.println("Saved car to database: " + state.allAttributes());
    }
}
