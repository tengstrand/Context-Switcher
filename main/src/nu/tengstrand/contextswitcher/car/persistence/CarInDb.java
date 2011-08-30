package nu.tengstrand.contextswitcher.car.persistence;

import nu.tengstrand.contextswitcher.FileWriter;
import nu.tengstrand.contextswitcher.car.Car;
import nu.tengstrand.contextswitcher.car.CarAsRowInFile;
import nu.tengstrand.contextswitcher.car.contextswitch.CarContextSwitcher;
import nu.tengstrand.contextswitcher.car.contextswitch.CarInternals;

/**
 * Represents a car as a record in a table in the database.
 */
public class CarInDb {
    private final CarInternals internals;
    private final CarContextSwitcher contextSwitcher;
    private final DbPersister dbPersister;

    /*
     * DO NOT USE THIS CONSTRUCTOR - use the CarFactory!
     */
    public CarInDb(CarInternals carInternals, CarContextSwitcher carContextSwitcher, DbPersister dbPersister) {
        internals = carInternals;
        contextSwitcher = carContextSwitcher;
        this.dbPersister = dbPersister;
    }

    public Car asCar() {
        return contextSwitcher.asCar();
    }

    public CarAsRowInFile asRowInFile(FileWriter fileWriter) {
        return contextSwitcher.asRowInFile(fileWriter);
    }

    public void save() {
        internals.primaryKey = dbPersister.save(internals.primaryKey, contextSwitcher);
        System.out.println("  '" + this + "' was saved to the database!");
    }

    public String toString() {
        return "CarInDb{" + internals + ", primaryKey=" + internals.primaryKey + "}";
    }
}
