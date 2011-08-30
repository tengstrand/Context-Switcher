package nu.tengstrand.contextswitcher.car;

import nu.tengstrand.contextswitcher.FileWriter;
import nu.tengstrand.contextswitcher.car.contextswitch.CarContextSwitcher;
import nu.tengstrand.contextswitcher.car.contextswitch.CarInternals;
import nu.tengstrand.contextswitcher.car.persistence.DbPersister;
import nu.tengstrand.contextswitcher.car.persistence.CarInDb;

/**
 * Represents a car as the business layer wants to see it.
 */
public class Car {
    private final CarInternals internals;
    private final CarContextSwitcher contextSwitcher;

    /*
     * DO NOT USE THIS CONSTRUCTOR - use the CarFactory!
     */
    public Car(CarInternals carInternals, CarContextSwitcher carContextSwitcher) {
        internals = carInternals;
        contextSwitcher = carContextSwitcher;
    }

    public CarInDb asCarInDb(DbPersister dbPersister) {
        return contextSwitcher.asCarInDb(dbPersister);
    }

    public CarAsRowInFile asRowInFile(FileWriter fileWriter) {
        return contextSwitcher.asRowInFile(fileWriter);
    }

    /**
     * Here is an example of business logic that operates on the internal representation.
     */
    public boolean isBig() {
        return internals.lengthInCentimeters >= 400;
    }

    public String toString() {
        return "Car{" + internals + "}";
    }
}
