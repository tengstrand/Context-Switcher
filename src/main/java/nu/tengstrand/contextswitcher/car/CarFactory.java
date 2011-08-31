package nu.tengstrand.contextswitcher.car;

import nu.tengstrand.contextswitcher.car.banking.CarAsRowInFile;
import nu.tengstrand.contextswitcher.car.business.Car;
import nu.tengstrand.contextswitcher.car.persistence.DbPersister;
import nu.tengstrand.contextswitcher.FileWriter;
import nu.tengstrand.contextswitcher.car.persistence.CarInDb;

/**
 * Responsible for creating our "context dependent" cars.
 */
public class CarFactory {

    public static Car createCar(int lengthInCentimeter, String name) {
        CarInternals carInternals = new CarInternals(lengthInCentimeter, name);
        return new CarContextSwitcher(carInternals).asCar();
    }

    public static CarInDb createCarInDb(int lengthInCentimeter, String name, DbPersister dbPersister) {
        CarInternals carInternals = new CarInternals(lengthInCentimeter, name);
        return new CarContextSwitcher(carInternals).asCarInDb(dbPersister);
    }

    public static CarAsRowInFile createCarAsRowInFile(String rowInFile, FileWriter fileWriter) {
        return new CarAsRowInFile(rowInFile, fileWriter);
    }
}
