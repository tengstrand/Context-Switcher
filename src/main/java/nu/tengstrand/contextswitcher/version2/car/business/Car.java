package nu.tengstrand.contextswitcher.version2.car.business;

import nu.tengstrand.contextswitcher.version2.car.state.CarState;
import nu.tengstrand.contextswitcher.version2.car.context.Context;
import nu.tengstrand.contextswitcher.version2.car.exportimport.CarAsRow;
import nu.tengstrand.contextswitcher.version2.car.persistence.CarInDb;

public class Car {
    private final CarState state;
    private final Object attributes;
    private final SystemInfo systemInfo;

    /**
     * This class makes use of a context object.
     * Take a look at CarInDb if you want to study a simpler case.
     *
     * @param carState the internal state which should be valid and ready to encapsulate
     * @param context the context of the caller
     */
    public Car(CarState carState, Context context) {
        // Make sure that we have a valid and encapsulated state object.
        state = carState.ensureValidState();
        attributes = context.hasRightsToReadColor() ? state : new RestrictedAttributes(state);
        systemInfo = new SystemInfo(context.systemVersion);
    }

    public boolean isBig() {
        return state.lengthInCentimeters >= 400;
    }

    public CarInDb asCarInDb() {
        return new CarInDb(state);
    }

    public CarAsRow asCarAsRow() {
        return new CarAsRow(state);
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;

        return state.equals(((Car)o).state);
    }

    @Override
    public int hashCode() {
        return state.hashCode();
    }

    @Override
    public String toString() {
        return systemInfo + "Car{" + attributes + '}';
    }
}
