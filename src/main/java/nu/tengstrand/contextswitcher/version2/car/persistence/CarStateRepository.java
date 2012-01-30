package nu.tengstrand.contextswitcher.version2.car.persistence;

import nu.tengstrand.contextswitcher.version2.car.*;

public class CarStateRepository {

    /**
     * Fake implementation of a criteria based API.
     */
    public CarStates findBy(String criteria) {
        CarStates carStates = new CarStates();

        carStates.add(new CarState(1, 370, "Opel", CarColor.BLUE));
        carStates.add(new CarState(2, 380, "Renault", CarColor.BLUE));

        return carStates;
    }
}
