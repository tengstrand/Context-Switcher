package nu.tengstrand.contextswitcher.car.banking;

import nu.tengstrand.contextswitcher.FileWriter;
import nu.tengstrand.contextswitcher.car.CarContextSwitcher;
import nu.tengstrand.contextswitcher.car.CarInternals;
import nu.tengstrand.contextswitcher.car.business.Car;
import nu.tengstrand.contextswitcher.car.persistence.DbPersister;
import nu.tengstrand.contextswitcher.car.persistence.CarInDb;

/**
 * Represents a car in the context of reading and writing it to a file.
 * The two constructors needs to keep the two representations,
 * internals and rowInFile, in sync.
 *
 * File format, e.g "0479,Volvo":
 *   0-3 = Length in centimeters
 *   5-  = Name
 */
public class CarAsRowInFile {
    private final CarInternals internals;
    private final CarContextSwitcher contextSwitcher;

    private String rowInFile;
    private FileWriter fileWriter;

    /**
     * DO NOT USE THIS CONSTRUCTOR - use the factory!
     *
     * @param rowInFile row in a text file.
     * @param fileWriter used by appendToFile() to append a car to the file.
     */
    public CarAsRowInFile(String rowInFile, FileWriter fileWriter) {
        this.rowInFile = rowInFile;
        this.fileWriter = fileWriter;

        internals = createCarInternals(); // We also need to set the internal representation.
        contextSwitcher = new CarContextSwitcher(internals);
    }

    private CarInternals createCarInternals() {
        int lengthInCentimeters = Integer.parseInt(rowInFile.substring(0,4));
        String name = rowInFile.substring(5);

        return new CarInternals(lengthInCentimeters, name);
    }

    /**
     * DO NOT USE THIS CONSTRUCTOR - use the CarFactory!
     */
    public CarAsRowInFile(CarInternals carInternals, CarContextSwitcher carContextSwitcher, FileWriter fileWriter) {
        internals = carInternals;
        contextSwitcher = carContextSwitcher;
        this.fileWriter = fileWriter;
        setRowInFile(); // We also need to set the "row in file" representation.
    }

    private void setRowInFile() {
        String lengthCm = "000" + internals.lengthInCentimeters;
        rowInFile = lengthCm.substring(8-lengthCm.length()) + "," + internals.name;
    }

    public Car asCar() {
        return contextSwitcher.asCar();
    }

    public CarInDb asCarInDb(DbPersister dbPersister) {
        return contextSwitcher.asCarInDb(dbPersister);
    }

    public void appendToFile() {
        fileWriter.appendToFile(rowInFile);
    }

    public String toString() {
        return "CarAsRowInFile{\"" + rowInFile + "\"}";
    }
}
