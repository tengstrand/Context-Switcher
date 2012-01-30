package nu.tengstrand.contextswitcher.version2.car.export;

import nu.tengstrand.contextswitcher.version2.car.CarState;
import nu.tengstrand.contextswitcher.version2.car.CarStates;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class CarsAsRow implements Iterable<CarAsRow> {
    private final List<CarAsRow> cars = new ArrayList<CarAsRow>();

    public CarsAsRow(CarStates states) {
        for (CarState state : states) {
            cars.add(new CarAsRow(state));
        }
    }

    public Iterator<CarAsRow> iterator() {
        return cars.iterator();
    }
}
