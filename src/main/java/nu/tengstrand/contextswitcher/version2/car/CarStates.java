package nu.tengstrand.contextswitcher.version2.car;

import nu.tengstrand.contextswitcher.version2.car.state.CarState;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class CarStates implements Iterable<CarState> {
    private List<CarState> states = new ArrayList<CarState>();

    public void add(CarState carState) {
        states.add(carState);
    }

    public int size() {
        return states.size();
    }

    public boolean isValid() {
        for (CarState state : states) {
            if (!state.isValid()) {
                return false;
            }
        }
        return true;
    }

    public Iterator<CarState> iterator() {
        return states.iterator();
    }
}
