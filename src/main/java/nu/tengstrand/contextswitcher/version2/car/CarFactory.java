package nu.tengstrand.contextswitcher.version2.car;

import nu.tengstrand.contextswitcher.version2.car.export.CarStateAsRow;

public class CarFactory {

    public CarSwitcher create(int lengthInCentimeters, String name, CarColor color) {
        return new CarSwitcher(new CarState(lengthInCentimeters, name, color));
    }

    public CarStateAsRow createFromRow(String row) {
        return new CarStateAsRow(row);
    }

    public CarSwitcherCreator.LenghInCentimeter create() {
        return CarSwitcherCreator.create();
    }
}
