package nu.tengstrand.contextswitcher.version2.car.state;

import nu.tengstrand.contextswitcher.version2.car.business.Car;
import nu.tengstrand.contextswitcher.version2.car.context.Context;
import nu.tengstrand.contextswitcher.version2.car.export.CarAsRowInFile;
import nu.tengstrand.contextswitcher.version2.car.persistence.CarInDb;

/**
 * A very common design principle is to design different parts of the system
 * into layers where a layer can access layers below but not above them.
 *
 * In this example application we consider the persistence layer as the
 * lowest layer so we don't want that layer to have direct access to any other layer.
 * The solution is to let it have access to the neutral ground where this class lives.
 *
 * This construct will permit higher layers to access lower levels and then "transform"
 * (context-switch) them into higher layers.
 */
public class CarStateSwitcher {
    private final CarState state;

    public CarStateSwitcher(CarState state) {
        this.state = state;
    }

    public Car car(Context context) {
        return new Car(state, context);
    }

    public CarAsRowInFile carAsRowInFile() {
        return new CarAsRowInFile(state);
    }

    public CarInDb carInDb() {
        return new CarInDb(state);
    }
}
