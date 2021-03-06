package nu.tengstrand.contextswitcher.version2.car.exportimport;

import nu.tengstrand.contextswitcher.version2.car.CarStateAsStrings;
import nu.tengstrand.contextswitcher.version2.car.state.CarCreator;

/**
 * Responsible for converting a row in the format (e.g) "480,Volvo,RED"
 * into a format where every attribute is a string: "480", "Volvo", "RED".
 */
public class CarStateAsRow {
    private final String row;

    public CarStateAsRow(String row) {
        this.row = row;
    }

    /**
     * Performs a "complete validation" of the car state, all the way down to CarState
     * to make sure that we can wrap behaviour around this state object.
     */
    public boolean isValid() {
        try {
            return asStrings().isValid();
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Encapsulates the car state and adds behaviour that is meaningful in the Car context.
     */
    public CarCreator create() {
        return new CarCreator(asStrings().asCarState());
    }

    /**
     * Converts the row format (e.g) "480,Volvo,RED" to an instance of CarStateAsStrings.
     *
     * @throws IllegalArgumentException if the row could not be converted
     */
    private CarStateAsStrings asStrings() {
        if (row == null) {
            throw new IllegalArgumentException("Row can not be null");
        }
        String[] values = row.split(",");

        if (values.length != 3) {
            throw new IllegalArgumentException("Expected to find three values in the row");
        }
        return new CarStateAsStrings(values[0], values[1], values[2]);
    }

    @Override
    public String toString() {
        return "CarStateAsRow{" + row + '}';
    }
}
