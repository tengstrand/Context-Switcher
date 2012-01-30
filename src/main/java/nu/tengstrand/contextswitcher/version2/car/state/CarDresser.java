package nu.tengstrand.contextswitcher.version2.car.state;

import nu.tengstrand.contextswitcher.version2.car.business.Car;
import nu.tengstrand.contextswitcher.version2.car.context.Context;
import nu.tengstrand.contextswitcher.version2.car.export.CarAsRow;
import nu.tengstrand.contextswitcher.version2.car.persistence.CarInDb;
import nu.tengstrand.contextswitcher.version2.car.state.CarState;

/**
 * This class is used to simplify creation of Car, CarInDb and CarAsRow.
 */
public class CarDresser {
    // This little trick will remove the need of getters of setters!
    public final CarState state;

    public CarDresser(CarState state) {
        this.state = state;
    }

    public boolean isValid() {
        return state.isValid();
    }

    /**
     * Encapsulates the car state and adds behaviour that is meaningful in the Car context.
     * The check if the state is valid should be checked performed by the Car constructor.
     */
    public Car asCar(Context context) {
        return new Car(state.asValidEncapsulatedState(), context);
    }

    /**
     * Encapsulates the car state and adds behaviour that is meaningful in the CarInDb context.
     */
    public CarInDb asCarInDb() {
        return new CarInDb(state.asValidEncapsulatedState());
    }

    /**
     * Encapsulates the car state and adds behaviour that is meaningful in the CarAsRow context.
     */
    public CarAsRow asCarAsRow() {
        return new CarAsRow(state.asValidEncapsulatedState());
    }
}
