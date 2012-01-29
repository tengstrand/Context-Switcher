package nu.tengstrand.contextswitcher.version2.car;

import nu.tengstrand.contextswitcher.version2.car.export.CarStateAsRow;
import nu.tengstrand.contextswitcher.version2.car.state.PublicCarState;

public class CarFactory {

    public PublicCarState create(int lengthInCentimeters, String name, CarColor color) {
        return new PublicCarState(lengthInCentimeters, name, color);
    }

    public CarStateAsRow createFromRow(String row) {
        return new CarStateAsRow(row);
    }

    public PublicCarStateCreator.LenghInCentimeter create() {
        return PublicCarStateCreator.create();
    }
}
