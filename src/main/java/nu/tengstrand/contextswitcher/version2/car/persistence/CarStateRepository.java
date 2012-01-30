package nu.tengstrand.contextswitcher.version2.car.persistence;

import nu.tengstrand.contextswitcher.version2.car.*;
import nu.tengstrand.contextswitcher.version2.car.state.CarState;

public class CarStateRepository {

    /**
     * Fake implementation of a criteria based API.
     */
    public CarStates findBy(String criteria) {
        CarStates carStates = new CarStates();

        carStates.add(new CarState(1, 370, "Opel", CarColor.BLUE));
        carStates.add(new CarState(2, 380, "Renault", CarColor.BLUE));

        System.out.println("Number of retrieved cars: " + carStates.size());

        return carStates;
    }
}
