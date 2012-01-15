package nu.tengstrand.contextswitcher.version2.car.business;

import nu.tengstrand.contextswitcher.version2.car.persistence.CarInDb;
import nu.tengstrand.contextswitcher.version2.car.state.CarState;

public class Car {
    private final CarState state;

    public Car(CarState carState) {
        state = carState;
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

    @Override
    public String toString() {
        return "Car{" + state + '}';
    }
}
