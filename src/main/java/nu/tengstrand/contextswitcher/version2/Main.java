package nu.tengstrand.contextswitcher.version2;

import nu.tengstrand.contextswitcher.version2.car.CarFactory;
import nu.tengstrand.contextswitcher.version2.car.business.Car;
import nu.tengstrand.contextswitcher.version2.car.context.Context;
import nu.tengstrand.contextswitcher.version2.car.context.UserRole;
import nu.tengstrand.contextswitcher.version2.car.export.CarStateAsRow;
import nu.tengstrand.contextswitcher.version2.car.persistence.CarInDb;
import nu.tengstrand.contextswitcher.version2.car.persistence.CarRepository;
import nu.tengstrand.contextswitcher.version2.car.persistence.Database;
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
        Context context = new Context();
        CarFactory carFactory = new CarFactory(context);
        CarRepository carRepository = new CarRepository(context);

        Database database = new Database();

        //---
        //--- 1. Build state
        //---

        // Example 1a - build state by from argument list.
        example("1a");
        Car volvo = carFactory.create(480, "Volvo", RED).asCar();
        System.out.println("volvo.isBig(): " + volvo.isBig());

        // Example 1b - build state by using the the pattern ChainedCreator.
        example("1b");
        CarInDb lamborghini = carFactory.create()
                .lengthInCentimeters(479)
                .name("Lamborghini")
                .color(RED).asCarInDb();
        System.out.println("lamborghini.isPersisted(): " + lamborghini.isPersisted());

        // Example 1c - build state from a comma separated row.
        example("1c");
        Car fiat = carFactory.createFromRow("384,Fiat,WHITE").asCar();
        System.out.println("fiat: " + fiat);

        //---
        //--- 2. Validate
        //---

        // Example 2a - check if the state is valid.
        example("2a");
        CarStateAsRow fiatRow = carFactory.createFromRow("15,Fiat,WHITE");
        System.out.println("fiatRow.isValid(): " + fiatRow.isValid());
        // Car importedFiat = fiatRow.asCar();  This will throw an IllegalStateException

        // Example 2b - fix invalid state.
        example("2b");
        PublicCarState saabState = carFactory.create(50, "Saab", GREEN);
        System.out.println("saabState.isValid(): " + saabState.isValid());
        saabState.lengthInCentimeters = 350;
        System.out.println("saabState.isValid(): " + saabState.isValid());
        Car saab = saabState.asCar();
        System.out.println("saab: " + saab);

        //---
        //--- 3. Context
        //---

        // Example 3a - switch to database context
        example("3a");
        CarInDb volvoInDb = volvo.asCarInDb();
        System.out.println("volvoInDb: " + volvoInDb);
        System.out.println("volvoInDb.isPersisted(): " + volvoInDb.isPersisted());
        volvoInDb.save(database);
        System.out.println("volvoInDb.isPersisted(): " + volvoInDb.isPersisted());

        example("3b");
        CarFactory newFactory = new CarFactory(new Context(UserRole.RESTRICTED));
        Car renault = carFactory.create(416, "Renault", BLUE).asCar();
        Car renaultInNewContext = newFactory.create(416, "Renault", BLUE).asCar();
        System.out.println("renault: " + renault);
        System.out.println("renaultInNewContext: " + renaultInNewContext);

        example("--------");




        PublicCarStates carStates = carRepository.findBy("color=BLUE");
        System.out.println("carStates.isValid(): " + carStates.isValid());
        System.out.println("carStates.asCarsInDb(): " + carStates.asCarsInDb());

        // Saves a car to database + export to file
        PublicCarState publicCarState = carFactory.create(424, "Porsche", BLACK);
        publicCarState.asCarInDb().save(database);
        PrintStream output = System.out; // Faking output to file
        publicCarState.asCarAsRow().export(output);
    }

    private static void example(String example) {
        System.out.println("\n*** Example " + example + " ***");
    }
}
