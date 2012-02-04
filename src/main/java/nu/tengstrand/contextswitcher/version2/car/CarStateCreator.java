package nu.tengstrand.contextswitcher.version2.car;

import nu.tengstrand.contextswitcher.version2.car.state.CarState;
import nu.tengstrand.contextswitcher.version2.car.state.CarCreator;

/**
 * This class makes use of the pattern Chained Creator to build a car state.
 */
public class CarStateCreator {
    private CarState state = new CarState();

    public class LenghInCentimeter {
        public Name lengthInCentimeters(int length) {
            CarStateCreator.this.state.lengthInCentimeters = length;
            return new Name();
        }
    }

    public class Name {
        public Color name(String name) {
            CarStateCreator.this.state.name = name;
            return new Color();
        }
    }

    public class Color {
        public CarCreator color(CarColor color) {
            CarStateCreator.this.state.color = color;
            return new CarCreator(state);
        }
    }
}
