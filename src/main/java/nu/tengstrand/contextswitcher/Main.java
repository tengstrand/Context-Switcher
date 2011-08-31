package nu.tengstrand.contextswitcher;

import nu.tengstrand.contextswitcher.car.CarFactory;
import nu.tengstrand.contextswitcher.car.banking.CarAsRowInFile;
import nu.tengstrand.contextswitcher.car.business.Car;
import nu.tengstrand.contextswitcher.car.persistence.DbPersister;
import nu.tengstrand.contextswitcher.car.persistence.CarInDb;

public class Main {

    /**
     * This is an example of the pattern Context Switcher.
     *
     * Author: Joakim Tengstrand, august 2011.
     */
    public static void main(String[] args) {
        // 1. Create an instance of Car and run some business logic.
        //    Only the method isBig(), that think cars >= 400 cm are big,
        //    is exposed from this context.
        Car car = CarFactory.createCar(479, "Volvo");
        System.out.println("The car: " + car);
        printIsBig(car);

        // 2. Switch context to "car in database" and save to database.
        //    Only the method save() is exposed from this context.
        DbPersister dbPersister = new DbPersister();
        CarInDb carInDb = car.asCarInDb(dbPersister);
        carInDb.save();

        // 3. Change context to "as row in file" and append to file.
        //    Only the method appendToFile() is exposed from this context.
        FileWriter fileWriter = new FileWriter("Car.txt");
        CarAsRowInFile carToBeStoredInFile = carInDb.asRowInFile(fileWriter);
        carToBeStoredInFile.appendToFile();

        // 4. Read a car from file and change context to "business layer".
        String rowInFile = new FileReader("Car.txt").readNextRowFromFile();
        CarAsRowInFile carFromFile = CarFactory.createCarAsRowInFile(rowInFile, fileWriter);
        System.out.println("Row in file context: " + carFromFile);
        Car businessLayerCarFromFile = carFromFile.asCar();
        System.out.println("Business layer context: " + businessLayerCarFromFile);
        printIsBig(businessLayerCarFromFile);
    }

    private static void printIsBig(Car car) {
        String not = car.isBig() ? "" : "not ";
        System.out.println("This car is " + not + "big!");
    }
}
