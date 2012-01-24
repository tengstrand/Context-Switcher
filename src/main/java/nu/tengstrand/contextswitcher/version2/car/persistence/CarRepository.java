package nu.tengstrand.contextswitcher.version2.car.persistence;

import nu.tengstrand.contextswitcher.version2.car.CarColor;
import nu.tengstrand.contextswitcher.version2.car.context.Context;
import nu.tengstrand.contextswitcher.version2.car.state.PublicCarState;
import nu.tengstrand.contextswitcher.version2.car.state.PublicCarStates;

public class CarRepository {
    private final Context context;

    public CarRepository(Context context) {
        this.context = context;
    }

    /**
     * Fake implementation of a criteria based API.
     */
    public PublicCarStates findBy(String criteria) {
        PublicCarStates carStates = new PublicCarStates();

        carStates.add(new PublicCarState(1, 370, "Opel", CarColor.BLUE, context));
        carStates.add(new PublicCarState(2, 380, "Renault", CarColor.BLUE, context));

        return carStates;
    }
}
