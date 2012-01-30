package nu.tengstrand.contextswitcher.version2.car.business;

import nu.tengstrand.contextswitcher.version2.car.CarState;
import nu.tengstrand.contextswitcher.version2.car.CarStates;
import nu.tengstrand.contextswitcher.version2.car.context.Context;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Cars implements Iterable<Car> {
    private final List<Car> cars = new ArrayList<Car>();

    public Cars(CarStates states, Context context) {
        for (CarState state : states) {
            cars.add(new Car(state, context));
        }
    }

    public Iterator<Car> iterator() {
        return cars.iterator();
    }
}
