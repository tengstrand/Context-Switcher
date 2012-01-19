package nu.tengstrand.contextswitcher.version2;

import nu.tengstrand.contextswitcher.version2.car.CarFactory;
import nu.tengstrand.contextswitcher.version2.car.business.Car;
import nu.tengstrand.contextswitcher.version2.car.export.CarStateAsRow;
import nu.tengstrand.contextswitcher.version2.car.persistence.CarInDb;
import nu.tengstrand.contextswitcher.version2.car.persistence.CarRepository;
import nu.tengstrand.contextswitcher.version2.car.persistence.DbPersister;
import nu.tengstrand.contextswitcher.version2.car.state.PublicCarState;
import nu.tengstrand.contextswitcher.version2.car.state.PublicCarStates;

import java.io.PrintStream;

import static nu.tengstrand.contextswitcher.version2.car.CarColor.*;

/**
 * This is not only an improved version of the Context Switcher pattern,
 * it also shows how we can build and maintain valid state and work with validations.
 */
public class Main {

    public static void main(String[] args) {
        DbPersister dbPersister = new DbPersister();
        CarRepository carRepository = new CarRepository();

        // 1. First we create the state, then we choose representation based on
        // the context and type of work we want to do.
        Car volvo = CarFactory.create(479, "Volvo", BLUE).asCar();
        System.out.println("1. volvo.isBig(): " + volvo.isBig());
        CarInDb volvoInDb = volvo.asCarInDb();
        System.out.println("2. volvoInDb.isPersisted(): " + volvoInDb.isPersisted() + "\n");
        newLine();

        // 2. We can perform validations before we choose representation.
        //    If the state is not valid, the method asCar() will throw an IllegalStateException.
        CarStateAsRow fiatRow = CarStateAsRow.createFromRow("384,Fiat,WHITE");
        System.out.println("3. " + fiatRow + ", isValid: " + fiatRow.isValid());
        Car fiat = fiatRow.asCar();
        System.out.println("4. fiat.isBig(): " + fiat.isBig());
        newLine();

        // 3. An invalid state is created (length < 100) then we fix the state and dresses up as Car
        //    (to be able to perform "Car" work!).
        PublicCarState saabState = CarFactory.create(50, "Saab", GREEN);
        System.out.println("5. saabState.isValid(): " + saabState.isValid());
        saabState.lengthInCentimeters = 350;
        System.out.println("6. saabState.isValid(): " + saabState.isValid());
        Car saab = saabState.asCar();
        System.out.println("7. Saab as car: " + saab);
        newLine();

        // 4. Demonstrates how the pattern Chained Creator can be used to improve the API.
        CarInDb lamborghini = CarFactory.create()
                .lengthInCentimeters(479)
                .name("Lamborghini")
                .color(RED).asCarInDb();
        System.out.println("8. lamborghini.isPersisted(): " + lamborghini.isPersisted());
        lamborghini.save(dbPersister);
        System.out.println("9. lamborghini.isPersisted(): " + lamborghini.isPersisted());
        newLine();

        PublicCarStates carStates = carRepository.findBy("color=BLUE");
        System.out.println("carStates.isValid(): " + carStates.isValid());
        System.out.println("carStates.asCarsInDb(): " + carStates.asCarsInDb());

        // Saves a car to database + export to file
        CarInDb porscheInDb = CarFactory.create(424, "Porsche", BLACK).asCarInDb().save(dbPersister);
        PrintStream output = System.out; // Faking output to file
        porscheInDb.as().carAsRowInFile().export(output);
    }

    private static void newLine() {
        System.out.println("\n");
    }
}
