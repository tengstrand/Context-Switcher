package nu.tengstrand.contextswitcher.version2.car;

import nu.tengstrand.contextswitcher.version2.car.state.CarState;
import nu.tengstrand.contextswitcher.version2.car.state.CarDresser;

/**
 * This class make use of the pattern Chained Creator to improve the API
 * when creating an instance of PublicCarState.
 */
public class CarDresserCreator {
    private int lengthInCentimeters;
    private String name;
    private CarColor color;

    /**
     * Starting point of the constructor chain which ends with a PublicCarState.
     */
    public static LenghInCentimeter create() {
        return new CarDresserCreator().new LenghInCentimeter();
    }

    public class LenghInCentimeter {
        public Name lengthInCentimeters(int length) {
            CarDresserCreator.this.lengthInCentimeters = length;
            return new Name();
        }
    }

    public class Name {
        public Color name(String name) {
            CarDresserCreator.this.name = name;
            return new Color();
        }
    }

    public class Color {
        public CarDresser color(CarColor color) {
            CarDresserCreator.this.color = color;
            return new CarDresser(new CarState(lengthInCentimeters, name, color));
        }
    }
}
