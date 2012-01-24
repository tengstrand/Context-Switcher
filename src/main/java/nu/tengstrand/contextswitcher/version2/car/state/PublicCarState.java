package nu.tengstrand.contextswitcher.version2.car.state;

import nu.tengstrand.contextswitcher.version2.car.CarColor;
import nu.tengstrand.contextswitcher.version2.car.business.Car;
import nu.tengstrand.contextswitcher.version2.car.context.Context;
import nu.tengstrand.contextswitcher.version2.car.export.CarAsRowInFile;
import nu.tengstrand.contextswitcher.version2.car.persistence.CarInDb;

/**
 * This class is responsible for creating valid instances of
 * Car, CarInDb and CarAsRowInFile.
 *
 * We want to force the use of PublicCarState when creating new instances
 * of Car, CarInDb and CarAsRowInFile. That is the reason why this is
 * the only class that has the rights to create instances of CarState.
 *
 * We don't want to get into a situation where the user creates an
 * instance of CarState, sends it in to the constructor of (e.g) Car
 * and then mutate the state of 'car' via 'carState'.
 */
public class PublicCarState {
    public Integer primaryKey;
    public int lengthInCentimeters;
    public String name;
    public CarColor color;

    private final Context context;

    public PublicCarState(int lengthInCentimeters, String name, CarColor color, Context context) {
        this.lengthInCentimeters = lengthInCentimeters;
        this.name = name;
        this.color = color;
        this.context = context;
    }

    public PublicCarState(int primaryKey, int lengthInCentimeters, String name, CarColor color, Context context) {
        this(lengthInCentimeters, name, color, context);
        this.primaryKey = primaryKey;
    }

    /**
     * Creates a valid CarState and throws an exception if the state is not valid.
     *
     * @return a valid CarState
     */
    CarState carState() {
        CarState state = createState();

        if (!state.isValid()) {
            throw new IllegalStateException("Invalid car state");
        }
        return state;
    }

    public boolean isValid() {
        return createState().isValid();
    }

    private CarState createState() {
        if (primaryKey == null) {
            return new CarState(lengthInCentimeters, name, color);
        }
        return new CarState(primaryKey, lengthInCentimeters, name, color);
    }

    /**
     * Encapsulates the car state and adds behaviour that is meaningful in the Car context.
     */
    public Car asCar() {
        return new Car(carState(), context);
    }

    /**
     * Encapsulates the car state and adds behaviour that is meaningful in the CarInDb context.
     */
    public CarInDb asCarInDb() {
        return new CarInDb(carState());
    }

    /**
     * Encapsulates the car state and adds behaviour that is meaningful in the CarAsRow context.
     */
    public CarAsRowInFile asCarAsRow() {
        return new CarAsRowInFile(carState());
    }
}
