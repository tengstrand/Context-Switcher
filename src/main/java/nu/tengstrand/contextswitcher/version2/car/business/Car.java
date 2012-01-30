package nu.tengstrand.contextswitcher.version2.car.business;

import nu.tengstrand.contextswitcher.version2.car.CarState;
import nu.tengstrand.contextswitcher.version2.car.context.Context;
import nu.tengstrand.contextswitcher.version2.car.persistence.CarInDb;

public class Car {
    private final CarState state;
    private final Object attributes;
    private final SystemInfo systemInfo;

    public Car(CarState carState, Context context) {
        // Make sure the state is valid and that we have no references to the outside world!
        state = carState.validCopy();
        attributes = context.hasRightsToReadColor() ? state : new RestrictedAttributes(state);
        systemInfo = new SystemInfo(context.systemVersion);
    }

    public CarInDb asCarInDb() {
        return new CarInDb(state);
    }

    public boolean isBig() {
        return state.lengthInCentimeters >= 400;
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

    /**
     * The role "user" is not allowed to see
     *
     * @return
     */
    @Override
    public String toString() {
        return systemInfo + "Car{" + attributes + '}';
    }
}
