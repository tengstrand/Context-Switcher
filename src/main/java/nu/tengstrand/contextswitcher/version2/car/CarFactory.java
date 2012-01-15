package nu.tengstrand.contextswitcher.version2.car;

import nu.tengstrand.contextswitcher.version2.car.state.PublicCarState;

public class CarFactory {

    public static PublicCarState create(int lengthInCentimeters, String name, CarColor color) {
        return new PublicCarState(lengthInCentimeters, name, color);
    }

    public static PublicCarStateCreator.LenghInCentimeter create() {
        return PublicCarStateCreator.create();
    }
}
