package nu.tengstrand.contextswitcher.car;

import nu.tengstrand.contextswitcher.car.banking.CarAsRowInFile;
import nu.tengstrand.contextswitcher.car.business.Car;
import nu.tengstrand.contextswitcher.car.persistence.DbPersister;
import nu.tengstrand.contextswitcher.FileWriter;
import nu.tengstrand.contextswitcher.car.persistence.CarInDb;

/**
 * This class is responsible for switching between cars that are tailor-made for a specific context.
 */
public class CarContextSwitcher {
    private final CarInternals internals;

    // Context swithable car representations
    private Car car;
    private CarInDb carInDb;
    private CarAsRowInFile carAsRowInFile;

    public CarContextSwitcher(CarInternals carInternals) {
        internals = carInternals;
    }

    public Car asCar() {
        if (car == null) {
            car = new Car(internals, this);
        }
        return car;
    }

    public CarInDb asCarInDb(DbPersister dbPersister) {
        if (carInDb == null) {
            carInDb = new CarInDb(internals, this, dbPersister);
        }
        return carInDb;
    }

    public CarAsRowInFile asRowInFile(FileWriter fileWriter) {
        if (carAsRowInFile == null) {
            carAsRowInFile = new CarAsRowInFile(internals, this, fileWriter);
        }
        return carAsRowInFile;
    }

    public String toString() {
        return "CarContextSwitcher{internals=" + internals + "}";
    }
}
