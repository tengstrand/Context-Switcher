package nu.tengstrand.contextswitcher.car;

import nu.tengstrand.contextswitcher.car.banking.CarAsRowInFile;
import nu.tengstrand.contextswitcher.car.business.Car;
import nu.tengstrand.contextswitcher.car.persistence.DbPersister;
import nu.tengstrand.contextswitcher.FileWriter;
import nu.tengstrand.contextswitcher.car.persistence.CarInDb;

import java.util.HashMap;
import java.util.Map;

/**
 * This class is responsible for switching between cars that are tailor-made for a specific context.
 */
public class CarContextSwitcher {
    private final CarInternals internals;

    // Context swithable car representations
    private Car car;
    private CarAsRowInFile carAsRowInFile;
    private Map<DbPersister,CarInDb> carInDbs = new HashMap<DbPersister,CarInDb>();

    public CarContextSwitcher(CarInternals carInternals) {
        internals = carInternals;
    }

    public Car asCar() {
        if (car == null) {
            car = new Car(internals, this);
        }
        return car;
    }

    /**
     * The implementation of DbPersister may vary as we have to consider.
     */
    public CarInDb asCarInDb(DbPersister dbPersister) {
        if (carInDbs.containsKey(dbPersister)) {
            return carInDbs.get(dbPersister);
        }
        CarInDb carInDb = new CarInDb(internals, this, dbPersister);
        carInDbs.put(dbPersister, carInDb);

        return carInDb;
    }

    public CarAsRowInFile asRowInFile() {
        if (carAsRowInFile == null) {
            carAsRowInFile = new CarAsRowInFile(internals, this);
        }
        return carAsRowInFile;
    }

    public String toString() {
        return "CarContextSwitcher{internals=" + internals + "}";
    }
}
