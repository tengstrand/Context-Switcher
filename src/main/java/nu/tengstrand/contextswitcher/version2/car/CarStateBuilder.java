package nu.tengstrand.contextswitcher.version2.car;

import nu.tengstrand.contextswitcher.version2.car.business.Car;
import nu.tengstrand.contextswitcher.version2.car.context.Context;
import nu.tengstrand.contextswitcher.version2.car.exportimport.CarAsRow;
import nu.tengstrand.contextswitcher.version2.car.persistence.CarInDb;
import nu.tengstrand.contextswitcher.version2.car.state.CarCreator;
import nu.tengstrand.contextswitcher.version2.car.state.CarState;

public class CarStateBuilder {
    private CarState state = new CarState();
    private Creator creator = new Creator(state);

    public CarStateBuilder withLengthInCentimeters(int length) {
        state.lengthInCentimeters = length;
        return this;
    }

    public CarStateBuilder withName(String name) {
        state.name = name;
        return this;
    }

    public CarStateBuilder withColor(CarColor color) {
        state.color = color;
        return this;
    }

    public Creator as() {
        return creator;
    }

    /**
     * This construct is used to simplify the API so we can write:
     *    new CarStateBuilder().withName("Volvo").as().car();
     */
    public static class Creator {
        private CarCreator carCreator;

        Creator(CarState state) {
            carCreator = new CarCreator(state);
        }

        public Car car(Context context) {
            return carCreator.asCar(context);
        }

        public CarInDb carInDb() {
            return carCreator.asCarInDb();
        }

        public CarAsRow carAsRow() {
            return carCreator.asCarAsRow();
        }
    }
}
