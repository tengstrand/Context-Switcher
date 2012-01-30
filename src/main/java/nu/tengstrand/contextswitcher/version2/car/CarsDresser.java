package nu.tengstrand.contextswitcher.version2.car;

import nu.tengstrand.contextswitcher.version2.car.business.Cars;
import nu.tengstrand.contextswitcher.version2.car.context.Context;
import nu.tengstrand.contextswitcher.version2.car.export.CarAsRow;
import nu.tengstrand.contextswitcher.version2.car.export.CarsAsRow;
import nu.tengstrand.contextswitcher.version2.car.persistence.CarInDb;
import nu.tengstrand.contextswitcher.version2.car.persistence.CarsInDb;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Used to choose representation for a list of public car states.
 */
public class CarsDresser {
    private final CarStates states;

    public CarsDresser(CarStates states) {
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
