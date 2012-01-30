package nu.tengstrand.contextswitcher.version2.car;

/**
 * This class make use of the pattern Chained Creator to improve the API
 * when creating an instance of PublicCarState.
 */
public class CarSwitcherCreator {
    private int lengthInCentimeters;
    private String name;
    private CarColor color;

    /**
     * Starting point of the constructor chain which ends with a PublicCarState.
     */
    public static LenghInCentimeter create() {
        return new CarSwitcherCreator().new LenghInCentimeter();
    }

    public class LenghInCentimeter {
        public Name lengthInCentimeters(int length) {
            CarSwitcherCreator.this.lengthInCentimeters = length;
            return new Name();
        }
    }

    public class Name {
        public Color name(String name) {
            CarSwitcherCreator.this.name = name;
            return new Color();
        }
    }

    public class Color {
        public CarSwitcher color(CarColor color) {
            CarSwitcherCreator.this.color = color;
            return new CarSwitcher(new CarState(lengthInCentimeters, name, color));
        }
    }
}
