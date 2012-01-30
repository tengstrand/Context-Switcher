package nu.tengstrand.contextswitcher.version2.car;

import nu.tengstrand.contextswitcher.version2.car.export.CarStateAsRow;
import nu.tengstrand.contextswitcher.version2.car.state.CarState;
import nu.tengstrand.contextswitcher.version2.car.state.CarDresser;

public class CarFactory {

    public CarDresser create(int lengthInCentimeters, String name, CarColor color) {
        return new CarDresser(new CarState(lengthInCentimeters, name, color));
    }

    public CarStateAsRow createFromRow(String row) {
        return new CarStateAsRow(row);
    }

    public CarDresserCreator.LenghInCentimeter create() {
        return CarDresserCreator.create();
    }
}
