package nu.tengstrand.contextswitcher.version2.car.persistence;

import nu.tengstrand.contextswitcher.version2.car.state.CarState;
import nu.tengstrand.contextswitcher.version2.car.CarStates;
import nu.tengstrand.contextswitcher.version2.car.state.CarDresser;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class CarsInDb implements Iterable<CarInDb> {
    private List<CarInDb> cars = new ArrayList<CarInDb>();

    public CarsInDb(CarStates states) {
        for (CarState state : states) {
            cars.add(new CarDresser(state).asCarInDb());
        }
    }

    public Iterator<CarInDb> iterator() {
        return cars.iterator();
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;

        return cars.equals(((CarsInDb)o).cars);
    }

    @Override
    public int hashCode() {
        return cars.hashCode();
    }

    @Override
    public String toString() {
        return "CarsInDb{" + cars + '}';
    }

}
