package nu.tengstrand.contextswitcher.version2.car;

import nu.tengstrand.contextswitcher.version2.car.state.CarState;

/**
 * Represents a car state as strings.
 */
public class CarStateAsStrings {
    public String lengthInCentimeters;
    public String name;
    public String color;

    public CarStateAsStrings(String lengthInCentimeters, String name, String color) {
        this.lengthInCentimeters = lengthInCentimeters;
        this.name = name;
        this.color = color;
    }

    /**
     * @return converts the string based format
     */
    public CarState asCarState() {
        int length = Integer.parseInt(lengthInCentimeters);
        CarColor color = CarColor.valueOf(this.color);
        return new CarState(length, name, color);
    }

    public boolean isValid() {
        try {
            return asCarState().isValid();
        } catch (Exception e) {
            return false;
        }
    }
}
