package nu.tengstrand.contextswitcher.version2.car.persistence;

import nu.tengstrand.contextswitcher.version2.car.CarState;
import nu.tengstrand.contextswitcher.version2.car.CarStates;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class CarsInDb implements Iterable<CarInDb> {
    private List<CarInDb> cars = new ArrayList<CarInDb>();

    public CarsInDb(CarStates states) {
        for (CarState state : states) {
            cars.add(new CarInDb(state));
        }
    }

    public Iterator<CarInDb> iterator() {
        return cars.iterator();
    }
}
