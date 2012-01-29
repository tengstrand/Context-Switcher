package nu.tengstrand.contextswitcher.version2;

import nu.tengstrand.contextswitcher.version2.car.CarFactory;
import nu.tengstrand.contextswitcher.version2.car.business.Car;
import nu.tengstrand.contextswitcher.version2.car.context.*;
import nu.tengstrand.contextswitcher.version2.car.context.SystemVersion;
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
        SystemVersion systemVersion = SystemVersion.BASIC;
        User currentUser = new User(Role.DEFAULT);

        Context context = new Context(systemVersion, currentUser);
        CarFactory carFactory = new CarFactory();
        CarRepository carRepository = new CarRepository();

        Database database = new Database();

        //---
        //--- 1. Build state
        //---

        // Example 1a - build state by from argument list.
        example("1a");
        Car volvo = carFactory.create(480, "Volvo", RED).asCar(context);
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
        Car fiat = carFactory.createFromRow("384,Fiat,WHITE").asCar(context);
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
        Car saab = saabState.asCar(context);
        System.out.println("saab: " + saab);

        //---
        //--- 3. Work with different tasks
        //---

        // Example 3a - switch to database context
        example("3a");
        CarInDb volvoInDb = volvo.asCarInDb();
        System.out.println("volvoInDb: " + volvoInDb);
        System.out.println("volvoInDb.isPersisted(): " + volvoInDb.isPersisted());
        volvoInDb.save(database);
        System.out.println("volvoInDb.isPersisted(): " + volvoInDb.isPersisted());

        //---
        //--- 4. Context Oriented Programming (COP)
        //---

        // Example 4a - context aware code
        example("4a");

        // 1. Role = DEFAULT, SystemVersion = BASIC
        Car renault = carFactory.create(416, "Renault", BLUE).asCar(context);
        System.out.println("1. renault: " + renault);

        // 2. Role = RESTRICTED, SystemVersion = BASIC
        User restrictedUser = new User(Role.RESTRICTED);
        Context newContext = context.as(restrictedUser);
        Car renaultInNewContext1 = carFactory.create(416, "Renault", BLUE).asCar(newContext);
        System.out.println("2, renaultInNewContext1: " + renaultInNewContext1);

        // 3. Role = RESTRICTED, SystemVersion = ENTERPRISE
        Car renaultInNewContext2 = carFactory.create(416, "Renault", BLUE).asCar(newContext.in(SystemVersion.ENTERPRISE));
        System.out.println("3, renaultInNewContext2: " + renaultInNewContext2);

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
