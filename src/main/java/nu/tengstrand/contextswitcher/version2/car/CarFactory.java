package nu.tengstrand.contextswitcher.version2.car;

import nu.tengstrand.contextswitcher.version2.car.exportimport.CarStateAsRow;
import nu.tengstrand.contextswitcher.version2.car.state.CarState;
import nu.tengstrand.contextswitcher.version2.car.state.CarCreator;

/**
 * Performs the first step "create state" of an objects life cycle.
 */
public class CarFactory {

    public CarCreator create(int lengthInCentimeters, String name, CarColor color) {
        return new CarCreator(new CarState(lengthInCentimeters, name, color));
    }

    public CarCreator createFromRow(String row) {
        return new CarStateAsRow(row).create();
    }

    public CarStateCreator.LenghInCentimeter create() {
        return new CarStateCreator().new LenghInCentimeter();
    }
}
