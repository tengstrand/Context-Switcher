package nu.tengstrand.contextswitcher.version2.car;

import nu.tengstrand.contextswitcher.version2.car.business.Cars;
import nu.tengstrand.contextswitcher.version2.car.context.Context;
import nu.tengstrand.contextswitcher.version2.car.exportimport.CarsAsRow;
import nu.tengstrand.contextswitcher.version2.car.persistence.CarsInDb;

/**
 * Used to choose representation for a list of public car states.
 */
public class CarsCreator {
    private final CarStates states;

    public CarsCreator(CarStates states) {
        this.states = states;
    }

    public boolean isValid() {
        return states.isValid();
    }

    public Cars asCars(Context context) {
        return new Cars(states, context);
    }

    public CarsAsRow asCarsAsRow() {
        return new CarsAsRow(states);
    }

    public CarsInDb asCarsInDb() {
        return new CarsInDb(states);
    }
}
